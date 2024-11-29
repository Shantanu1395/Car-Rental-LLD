package CarRental.example.CarRentalLLD.repositories;

import CarRental.example.CarRentalLLD.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // Find vehicles by type, branchId, and availability
    List<Vehicle> findByTypeAndBranchIdAndIsAvailable(String type, Long branchId, boolean isAvailable);

    // Find a vehicle by its barcode
    Optional<Vehicle> findByBarcode(String barcode);

    // Find vehicles available at a branch
    List<Vehicle> findByBranchIdAndIsAvailable(Long branchId, boolean isAvailable);
}