package rw.medicine.stock.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue
    private UUID id;

    private String companyName;
    private String contactName;

    @Column(unique = true)
    private String supplierCode;

    private String phone;

    public Supplier() {}

    public UUID getId() { 
        return id; 
    }
    public void setId(UUID id) { 
        this.id = id; 
    }

    public String getCompanyName() { 
        return companyName; 
    }
    public void setCompanyName(String companyName) { 
        this.companyName = companyName; 
    }

    public String getContactName() {
         return contactName; 
        }
    public void setContactName(String contactName) { 
        this.contactName = contactName; 
    }

    public String getSupplierCode() {
        return  supplierCode;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode; 
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone ;
    }

}