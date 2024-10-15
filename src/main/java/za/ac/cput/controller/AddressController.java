package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.AddressId;
import za.ac.cput.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/save")
    public Address save(@RequestBody Address address) {
        return addressService.save(address);
    }

    @GetMapping("/read/{l}")
    public Address read(@PathVariable("l") Long l) {
       // AddressId addressId = new AddressId(street,postalCode);
        return addressService.read(l);
    }

    @PostMapping("/update")
    public Address update(@RequestBody Address address) {
        return addressService.save(address);
    }

    @DeleteMapping("/delete/{l}")
    public boolean delete(@PathVariable("l") Long l) {
        return addressService.deleteById(l);
    }

    @GetMapping("/getall")
    public List<Address> getAll() {
        return addressService.getall();
    }
    }
