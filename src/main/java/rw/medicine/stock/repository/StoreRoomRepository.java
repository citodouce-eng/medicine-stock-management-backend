package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.medicine.stock.domain.StoreRoom;

import java.util.List;
import java.util.UUID;

public interface StoreRoomRepository extends JpaRepository<StoreRoom, UUID>{

    @Query("SELECT s FROM StoreRoom s WHERE NOT EXISTS (SELECT p FROM Pharmacist p WHERE p.storeRoom = s) ORDER BY s.roomNumber ASC")
    List<StoreRoom> findFreeStoreRooms();
    
}
