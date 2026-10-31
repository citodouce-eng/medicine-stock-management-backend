package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.medicine.stock.domain.StoreRoom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreRoomRepository extends JpaRepository<StoreRoom, UUID>{

    @Query("SELECT s FROM StoreRoom s WHERE NOT EXISTS (SELECT p FROM Pharmacist p WHERE p.storeRoom = s) ORDER BY s.roomNumber ASC")
    List<StoreRoom> findFreeStoreRooms();
    
    Optional<StoreRoom> findByRoomNumber(int roomNumber);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pharmacist p WHERE p.storeRoom.roomNumber = :roomNumber")
    boolean existsByRoomNumberAndPharmacistsIsNotEmpty(@Param("roomNumber") int roomNumber);
}
