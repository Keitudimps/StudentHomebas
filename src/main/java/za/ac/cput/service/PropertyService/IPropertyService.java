package za.ac.cput.service.PropertyService;

import za.ac.cput.domain.Property;
import za.ac.cput.service.IService;

import java.util.List;

public interface IPropertyService extends IService<Property, Long> {
    List<Property> getAll();
}
