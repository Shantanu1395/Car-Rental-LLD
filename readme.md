**Problem Statement**
A Car Rental System is a software built to handle the renting of automobiles for a short period of time, generally ranging from a few hours to a few weeks. A car rental system often has numerous local branches (to allow its user to return a vehicle to a different location), and primarily located near airports or busy city areas.
1. The system will support the renting of different automobiles like cars, trucks, SUVs, vans, and motorcycles.
2. Each vehicle should be added with a unique barcode and other details, including a parking stall number which helps to locate the vehicle.
3. The system should be able to retrieve information like which member took a particular vehicle or what vehicles have been rented out by a specific member.
4. The system should collect a late-fee for vehicles returned after the due date.
5. Members should be able to search the vehicle inventory and reserve any available vehicle.
6. The system should be able to send notifications whenever the reservation is approaching the pick-up date, as well as when the vehicle is nearing the due date or has not been returned within the due date.
7. The system will be able to read barcodes from vehicles.
8. Members should be able to cancel their reservations.
9. The system should maintain a vehicle log to track all events related to the vehicles.
10. Members can add rental insurance to their reservation.
11. Members can rent additional equipment, like navigation, child seat, ski rack, etc.
12. Members can add additional services to their reservation, such as roadside assistance, additional driver, wifi, etc.

1. Usecases
    1. User
        1. Create User
        2. Login
        3. Search inventory
        4. Booking lifecycle
            1. Create Booking / Update / Delete
            2. Rent Add ons - roadside assistance, additional driver, wifi
            3. Book vehicle for a duration(hours, weeks, months)
            4. Checkout vehicle(should be booked)
            5. Update car logs
            6. Return Vehicle
            7. Cancel Reservations
    2. System
        1. Fetch which vehicle is rented by which user
        2. Collect Late fees after due date
        3. Push notification for vehicle pickup and return near dates
        4. Read barcodes from vehicles and book if applicable + add other details
        5. Vehicle Log to track all events for a vehicle
        6. CRUD Vehicles in garage
2. Architecture
    1. Has branches (can take vehicle from 1 branch and return to another)
    2. Vehicle - Cars, Trucks, SUV, VANs, Motorcycles | Barcode | Parking Stall Number

Lifecycle / Activity
1. Vehicles are onboarded to a garage and added to inventory
2. Admin scans vehicle for transaction metadata, can be used for servicing
3. Admin check vehicle dashboard for checking what vehicles are rented for supply / demand metrices and telling next available date to customers
4. User comes to app and register / login himself
5. User searches inventory
6. User selects a vehicle and make a booking with/without addons
1. User make payment for the booking along with security fees
7. User modify addons for booking
8. User modify / cancels his booking
9. System sends notification for vehicle pickup
10. User checkout his vehicle (fuel readings and snapshots are attached to booking)
11. System send notification for vehicle return
12. User returns vehicle
13. System generates receipt on the bases of fare + fuel fee + deduct money from security fees and return remaining money
14. Admin can remove vehicle from garage

http://localhost:8080/h2-console

**Models**
Branch {id, name, location, address} -1:m- Vehicle {id, licensePlate, type, branchId (FK), barcode, isAvailable, fuelLevel, mileage, lastServiced}
Booking {id, vehicleId (FK), userId (FK), startTime, endTime, status}
		-m:1- Vehicle {id, licensePlate, type, branchId (FK), barcode, isAvailable, fuelLevel, mileage, lastServiced}
        -m:1- User {id, username, email, password, contactDetails, role}
        -1:m- Fee {id, bookingId (FK), type, amount}
		-1:m- BookingEvent {id, bookingId (FK), event, description, timestamp}
		-1:m- Payment {id, bookingId (FK), amount, paymentMethod, status, timestamp}
        -m:n- AddOn {id, name, type, description, price}

**Interfaces**
```java
public interface BranchService { 
    Branch addBranch(String name, String location, String address);
    Branch getBranchById(Long branchId);
    List<Branch> getAllBranches();
}

public interface VehicleService { 
    Vehicle addVehicle(String licensePlate, String type, Long branchId, String barcode);
    List<Vehicle> getAvailableVehicles(Long branchId, String type);
    void updateVehicleStatus(Long vehicleId, boolean isAvailable);
    Vehicle getVehicleById(Long vehicleId);
}

public interface BookingService { 
    Booking createBooking(Long userId, Long vehicleId, LocalDateTime startTime, LocalDateTime endTime, List<Long> addOnIds);
    Booking modifyBooking(Long bookingId, LocalDateTime newStartTime, LocalDateTime newEndTime, List<Long> addOnIds);
    void cancelBooking(Long bookingId);
    Booking getBookingById(Long bookingId);
    List<Fee> calculateFeesForBooking(Long bookingId);
    double getTotalFee(Long bookingId);
}

public interface AddOnService { 
    AddOn addAddOn(String name, String type, String description, double price);
    List<AddOn> getAllAddOns();
}

public interface PaymentService { 
    Payment processPayment(Long bookingId, double amount, String paymentMethod);
    List<Payment> getPaymentsForBooking(Long bookingId);
}

public interface BookingEventService { 
    void logEvent(Long bookingId, String event, String description);
    List<BookingEvent> getEventsForBooking(Long bookingId);
}

public interface FeeCalculationStrategy { 
    double calculateFee(Booking booking, List<AddOn> addOns);
}

public interface NotificationStrategy { 
    void sendNotification(Long userId, String message);
}

public interface NotificationService { 
    void sendPickupNotification(Long bookingId);
    void sendReturnNotification(Long bookingId);
}
```

