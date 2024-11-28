package CarRental.example.CarRentalLLD.interfaces;

import CarRental.example.CarRentalLLD.models.Branch;

import java.util.List;

public interface BranchService {
    Branch addBranch(String name, String location, String address);
    Branch getBranchById(Long branchId);
    List<Branch> getAllBranches();
}
