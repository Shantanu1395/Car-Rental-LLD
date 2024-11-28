package CarRental.example.CarRentalLLD.services.strategies.feeCalculation;

import CarRental.example.CarRentalLLD.interfaces.strategy.FeeCalculationStrategy;
import CarRental.example.CarRentalLLD.models.AddOn;
import CarRental.example.CarRentalLLD.models.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultFeeCalculationStrategy implements FeeCalculationStrategy {

    @Override
    public double calculateFee(Booking booking, List<AddOn> addOns) {
        double baseFee = 100 * booking.getDuration();
        double addOnFees = addOns.stream().mapToDouble(AddOn::getPrice).sum();
        return baseFee + addOnFees;
    }
}
