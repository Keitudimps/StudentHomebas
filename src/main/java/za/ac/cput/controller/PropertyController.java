package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Property;
import za.ac.cput.domain.RejectionRequest;
import za.ac.cput.service.PropertyService.PropertyService;

import java.util.List;
@RestController
@RequestMapping("/Property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/create")
    public Property create(@RequestBody Property property) {
        return propertyService.save(property);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Property property = propertyService.read(id);

        if (property == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @PostMapping("/update")
    public  Property update(@RequestBody Property property) {
        return propertyService.update(property);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        return propertyService.deleteById(id);
    }

    public ResponseEntity<Property> approveProperty(@PathVariable Long propertyId) {
        Property approvedProperty = propertyService.approveProperty(propertyId);

        if (approvedProperty == null) {
            return ResponseEntity.notFound().build(); // Handle case when property is not found
        }

        return ResponseEntity.ok(approvedProperty);
    }


    @PutMapping("/{propertyId}/reject")
    public ResponseEntity<Property> rejectProperty(@PathVariable Long propertyId, @RequestBody RejectionRequest rejectionRequest) {
        Property rejectedProperty = propertyService.rejectProperty(propertyId, rejectionRequest.getRejectionReason());

        if (rejectedProperty == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rejectedProperty);
    }

    @GetMapping("/getAll")
    public List<Property> getAll() {
        return propertyService.getAll();
    }

}
