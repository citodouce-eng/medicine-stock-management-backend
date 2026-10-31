package rw.medicine.stock.domain;

import java.time.LocalDate;
import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "stock_entry")
public class StockEntry {


    @Id 
    @GeneratedValue
    private UUID id;

    private String medicineName;
    private String batchNote;
    private int quantity;
    private LocalDate receivedDate;

    @ManyToOne 
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder ;

    public StockEntry() {}

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id ;
    }
    public String getMedicineName() { 
        return medicineName;
     }
      public void setMedicineName(String medicineName) { 
        this.medicineName = medicineName;
     }
     public String getBatchNote() { 
        return batchNote;
     }
     public void setBatchNote(String batchNote) { 
        this.batchNote = batchNote; 
    }
     public int getQuantity() { 
        return quantity; 
    }
     public void setQuantity(int quantity) { 
        this.quantity = quantity;
    }
    public LocalDate getReceivedDate() {
        return receivedDate;
    }
    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }
     public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    
}
