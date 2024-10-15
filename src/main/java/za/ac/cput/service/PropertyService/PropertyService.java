package za.ac.cput.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.*;
import za.ac.cput.factory.StatusFactory;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.repository.DocumentRepository;
import za.ac.cput.repository.LandlordRepository;
import za.ac.cput.repository.PropertyRepository;
import za.ac.cput.service.AddressService;
import za.ac.cput.service.landlordService.LandlordService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService implements IPropertyService {

    @Autowired
    private PropertyRepository repo;


    @Autowired
    private LandlordService landlordService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private DocumentRepository documentRepository;


    @Override
    public Property save(Property property) {

        Property approvedProperty = new Property.Builder()
                .copy(property)
                .setStatus(StatusFactory.createPendingStatus())
                .build();
        return repo.save(approvedProperty);
    }

    @Override
    public Property read(Long s) {
        return repo.findById(s).orElse(null);
    }

    @Override
    public Property update(Property property) {
        return repo.save(property);
    }

    @Override
    public boolean deleteById(Long s) {
        repo.deleteById(s);
        return !repo.existsById(s);
    }
    @Override
    public List<Property> getAll() {
        return repo.findAll();

    }
    public Property approveProperty(Long propertyId) {
        Property property = repo.findById(propertyId).orElse(null);

        Property approvedProperty = new Property.Builder()
                .copy(property)
                .setStatus(StatusFactory.createApprovedStatus())
                .build();


        return repo.save(approvedProperty);
    }


    public Property rejectProperty(Long propertyId, String rejectionReason) {
        Property property = repo.findById(propertyId).orElse(null);

        Property rejectedProperty = new Property.Builder()
                                    .copy(property)
                                    .setStatus(StatusFactory.createRejectedStatus(rejectionReason))
                                    .build();


        return repo.save(rejectedProperty);
    }
}
