package rw.medicine.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
 
import java.util.List;
import java.util.UUID;

@Entity 
@Table(name = "medicine_class")
public class MedicineClass{

    @Id 
    @GeneratedValue 
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "medicineClasses")
    @JsonIgnore 
    private List<Pharmacist> pharmacists;
    public MedicineClass() {}

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name ;
    }
    public List<Pharmacist> getPharmacists() {
        return pharmacists;
    }
    public void setPharmacists(List<Pharmacist> pharmacists) {
    this.pharmacists = pharmacists;
}


}