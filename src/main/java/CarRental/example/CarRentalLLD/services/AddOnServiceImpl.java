package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.interfaces.AddOnService;
import CarRental.example.CarRentalLLD.models.AddOn;
import CarRental.example.CarRentalLLD.repositories.AddOnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddOnServiceImpl implements AddOnService {

    private final AddOnRepository addOnRepository;

    @Autowired
    public AddOnServiceImpl(AddOnRepository addOnRepository) {
        this.addOnRepository = addOnRepository;
    }

    @Override
    public AddOn addAddOn(String name, String type, String description, double price) {
        AddOn addOn = new AddOn(name, type, description, price);
        return addOnRepository.save(addOn);
    }

    @Override
    public List<AddOn> getAllAddOns() {
        return addOnRepository.findAll();
    }
}
