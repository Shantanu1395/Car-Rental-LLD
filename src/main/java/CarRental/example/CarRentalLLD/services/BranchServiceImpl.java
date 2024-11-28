package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.interfaces.BranchService;
import CarRental.example.CarRentalLLD.models.Branch;
import CarRental.example.CarRentalLLD.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch addBranch(String name, String location, String address) {
        Branch branch = new Branch(name, location, address);
        return branchRepository.save(branch);
    }

    @Override
    public Branch getBranchById(Long branchId) {
        return branchRepository.findById(branchId).orElseThrow(() -> new EntityNotFoundException("Branch not found"));
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
}
