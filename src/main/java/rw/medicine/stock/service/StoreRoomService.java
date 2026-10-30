package rw.medicine.stock.service;

import org.springframework.stereotype.Service;
import rw.medicine.stock.domain.StoreRoom;
import rw.medicine.stock.repository.StoreRoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreRoomService {

    private final StoreRoomRepository storeRoomRepository;

    public StoreRoomService(StoreRoomRepository storeRoomRepository) {
        this.storeRoomRepository = storeRoomRepository;
    }

    public List<StoreRoom> getAll() {
        return storeRoomRepository.findAll();
    }

    public Optional<StoreRoom> getById(UUID id) {
        return storeRoomRepository.findById(id);
    }

    public StoreRoom save(StoreRoom storeRoom) {
        return storeRoomRepository.save(storeRoom);
    }

    public String delete(UUID id) {
        if (!storeRoomRepository.existsById(id)) {
            return "The store room with that id does not exist";
        }
        storeRoomRepository.deleteById(id);
        return "Store room deleted successfully";
    }
}