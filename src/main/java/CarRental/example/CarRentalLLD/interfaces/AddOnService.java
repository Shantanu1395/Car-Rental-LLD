package CarRental.example.CarRentalLLD.interfaces;

import CarRental.example.CarRentalLLD.models.AddOn;

import java.util.List;

public interface AddOnService {
    AddOn addAddOn(String name, String type, String description, double price);
    List<AddOn> getAllAddOns();
}
