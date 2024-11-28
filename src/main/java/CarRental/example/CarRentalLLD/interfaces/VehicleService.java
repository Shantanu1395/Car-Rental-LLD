package CarRental.example.CarRentalLLD.interfaces;

import CarRental.example.CarRentalLLD.models.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle addVehicle(String licensePlate, String type, Long branchId, String barcode);
    List<Vehicle> getAvailableVehicles(Long branchId, String type);
    void updateVehicleStatus(Long vehicleId, boolean isAvailable);
    Vehicle getVehicleById(Long vehicleId);
}
