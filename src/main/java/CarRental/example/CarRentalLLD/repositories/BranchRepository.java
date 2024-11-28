package CarRental.example.CarRentalLLD.repositories;

import CarRental.example.CarRentalLLD.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    // Find branches by location
    List<Branch> findByLocation(String location);
}
