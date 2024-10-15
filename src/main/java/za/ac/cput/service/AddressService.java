package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.AddressId;
import za.ac.cput.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {

        if (address != null) {
            System.out.println("checking if existing address exists");
           // AddressId addressId = new AddressId(address.getStreet(), address.getPostalCode());
            Optional<Address> existingAddress = addressRepository.findById(address.getAddressID());
            if (existingAddress.isPresent()) {
                System.out.println("found address");
                address = existingAddress.get();
            } else {
                System.out.println("saving new address");
                address = addressRepository.save(address);
                System.out.println("Saved");
            }
        }
        return address;
    }

    @Override
    public Address read(Long o) {
        return addressRepository.findById(o).orElse(null);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public boolean deleteById(Long o) {
        addressRepository.deleteById(o);
        return !addressRepository.existsById(o);
    }
    @Override
    public List<Address> getall() {
        return addressRepository.findAll();
    }
}
