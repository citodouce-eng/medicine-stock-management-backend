package rw.medicine.stock.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity 
@Table(name = "store_room")
public class StoreRoom {

    @Id 
    @GeneratedValue
    private UUID id;
    
    private int roomNumber;
    private String name;

    public StoreRoom() {}

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int rooNumber) {
        this.roomNumber = roomNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
