package CarRental.example.CarRentalLLD.controllers;

import CarRental.example.CarRentalLLD.interfaces.BranchService;
import CarRental.example.CarRentalLLD.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> addBranch(@RequestBody Branch branch) {
        Branch createdBranch = branchService.addBranch(branch.getName(), branch.getLocation(), branch.getAddress());
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        Branch branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
}