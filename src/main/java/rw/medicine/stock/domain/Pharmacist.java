package rw.medicine.stock.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pharmacist")
public class Pharmacist {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String licenceNumber;

    @ManyToOne
    @JoinColumn(name = "store_room_id")
    private StoreRoom storeRoom;

    @ManyToMany
    @JoinTable(
            name = "pharmacist_medicine_class",
            joinColumns = @JoinColumn(name = "pharmacist_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_class_id")
    )
    private List<MedicineClass> medicineClasses;

    public Pharmacist() {}

    public UUID getId() {
         return id;
     }
    public void setId(UUID id) { 
        this.id = id; 
    }

    public String getFirstName() {
         return firstName; 
        }
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }

    public String getLastName() { 
        return lastName; 
    }
    public void setLastName(String lastName) { 
        this.lastName = lastName;
     }

    public String getLicenceNumber() { 
        return licenceNumber; 
    }
    public void setLicenceNumber(String licenceNumber) { 
        this.licenceNumber = licenceNumber;
     }

    public StoreRoom getStoreRoom() { 
        return storeRoom; 
    }
    public void setStoreRoom(StoreRoom storeRoom) { 
        this.storeRoom = storeRoom; 
    }

    public List<MedicineClass> getMedicineClasses() {
        return medicineClasses;
    }
    public void setMedicineClasses(List<MedicineClass> medicineClasses) {
        this.medicineClasses = medicineClasses;
    }


}
