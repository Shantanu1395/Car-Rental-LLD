package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.interfaces.VehicleService;
import CarRental.example.CarRentalLLD.models.Branch;
import CarRental.example.CarRentalLLD.models.Vehicle;
import CarRental.example.CarRentalLLD.repositories.BranchRepository;
import CarRental.example.CarRentalLLD.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, BranchRepository branchRepository) {
        this.vehicleRepository = vehicleRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Vehicle addVehicle(String licensePlate, String type, Long branchId, String barcode) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found with ID: " + branchId));

        Vehicle vehicle = new Vehicle(licensePlate, type, barcode, branch, true);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAvailableVehicles(Long branchId, String type) {
        return vehicleRepository.findByTypeAndBranchIdAndIsAvailable(type, branchId, true);
    }

    @Override
    public void updateVehicleStatus(Long vehicleId, boolean isAvailable) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        vehicle.setIsAvailable(isAvailable);
        vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));
        vehicleRepository.delete(vehicle);
    }
}
