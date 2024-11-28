package CarRental.example.CarRentalLLD.interfaces.strategy;

import CarRental.example.CarRentalLLD.models.AddOn;
import CarRental.example.CarRentalLLD.models.Booking;

import java.util.List;

public interface FeeCalculationStrategy {
    double calculateFee(Booking booking, List<AddOn> addOns);
}