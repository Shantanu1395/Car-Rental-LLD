package CarRental.example.CarRentalLLD.controllers;

import CarRental.example.CarRentalLLD.dtos.VehicleRequest;
import CarRental.example.CarRentalLLD.interfaces.VehicleService;
import CarRental.example.CarRentalLLD.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody VehicleRequest vehicle) {
        Vehicle createdVehicle = vehicleService.addVehicle(vehicle.getLicensePlate(), vehicle.getType(), vehicle.getBranchId(), vehicle.getBarcode());
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Vehicle>> getAvailableVehicles(@RequestParam Long branchId, @RequestParam String type) {
        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles(branchId, type);
        return ResponseEntity.ok(availableVehicles);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateVehicleStatus(@PathVariable Long id, @RequestParam boolean isAvailable) {
        vehicleService.updateVehicleStatus(id, isAvailable);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }
}
