package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.interfaces.BookingService;
import CarRental.example.CarRentalLLD.interfaces.strategy.FeeCalculationStrategy;
import CarRental.example.CarRentalLLD.models.*;
import CarRental.example.CarRentalLLD.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final AddOnRepository addOnRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final FeeRepository feeRepository;
    private final FeeCalculationStrategy feeCalculationStrategy;
    private final ReentrantLock lock = new ReentrantLock();

    @Autowired
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            AddOnRepository addOnRepository,
            FeeRepository feeRepository,
            FeeCalculationStrategy feeCalculationStrategy,
            UserRepository userRepository,
            VehicleRepository vehicleRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.addOnRepository = addOnRepository;
        this.feeRepository = feeRepository;
        this.feeCalculationStrategy = feeCalculationStrategy;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    @Override
    public Booking createBooking(Long userId, Long vehicleId, LocalDateTime startTime, LocalDateTime endTime, List<Long> addOnIds) {

        boolean lockAcquired = false;

        try {
            // Attempt to acquire the lock
            lockAcquired = lock.tryLock(10, TimeUnit.SECONDS);

            if (!lockAcquired) {
                throw new IllegalStateException("Unable to acquire lock, booking already in progress");
            }
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id:" + vehicleId));
            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id:" + userId));

            Booking booking = new Booking(user, vehicle, startTime, endTime, "ACTIVE");

            Booking savedBooking = bookingRepository.save(booking);

            List<AddOn> addOns = addOnRepository.findAllById(addOnIds);
            double fee = feeCalculationStrategy.calculateFee(savedBooking, addOns);

            Fee bookingFee = new Fee(savedBooking, "BASE", fee);

            feeRepository.save(bookingFee);
            return savedBooking;
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while acquiring lock");
        } finally {
            if (lockAcquired) {
                lock.unlock();
            }
        }
    }

    @Override
    public Booking modifyBooking(Long bookingId, LocalDateTime newStartTime, LocalDateTime newEndTime, List<Long> addOnIds) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        booking.setStartTime(newStartTime);
        booking.setEndTime(newEndTime);

        Booking updatedBooking = bookingRepository.save(booking);

        List<AddOn> addOns = addOnRepository.findAllById(addOnIds);
        double fee = feeCalculationStrategy.calculateFee(updatedBooking, addOns);

        Fee bookingFee = new Fee(updatedBooking, "UPDATED", fee);
        feeRepository.save(bookingFee);
        return updatedBooking;
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
    }

    @Override
    public List<Fee> calculateFeesForBooking(Long bookingId) {
        return feeRepository.findByBookingId(bookingId);
    }

    @Override
    public double getTotalFee(Long bookingId) {
        return feeRepository.findByBookingId(bookingId).stream().mapToDouble(Fee::getAmount).sum();
    }
}
