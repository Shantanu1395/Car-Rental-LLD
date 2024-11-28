package CarRental.example.CarRentalLLD.controllers;

import CarRental.example.CarRentalLLD.interfaces.AddOnService;
import CarRental.example.CarRentalLLD.models.AddOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addons")
public class AddOnController {

    @Autowired
    private AddOnService addOnService;

    @PostMapping
    public ResponseEntity<AddOn> addAddOn(@RequestBody AddOn addOn) {
        AddOn createdAddOn = addOnService.addAddOn(addOn.getName(), addOn.getType(), addOn.getDescription(), addOn.getPrice());
        return new ResponseEntity<>(createdAddOn, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddOn>> getAllAddOns() {
        List<AddOn> addOns = addOnService.getAllAddOns();
        return ResponseEntity.ok(addOns);
    }
}
