package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.medicine.stock.domain.StoreRoom;

import java.util.UUID;

public interface StoreRoomRepository extends JpaRepository<StoreRoom, UUID>{
    
}
