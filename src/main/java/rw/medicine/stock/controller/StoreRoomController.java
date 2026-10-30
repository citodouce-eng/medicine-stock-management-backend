package rw.medicine.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.medicine.stock.domain.StoreRoom;
import rw.medicine.stock.service.StoreRoomService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/store-rooms")
public class StoreRoomController {

    private final StoreRoomService storeRoomService;

    public StoreRoomController(StoreRoomService storeRoomService) {
        this.storeRoomService = storeRoomService;
    }

    @GetMapping
    public List<StoreRoom> getAll() {
        return storeRoomService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreRoom> getById(@PathVariable UUID id) {
        Optional<StoreRoom> opt = storeRoomService.getById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(opt.get());
    }

    @PostMapping("/save")
    public ResponseEntity<StoreRoom> save(@RequestBody StoreRoom storeRoom) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storeRoomService.save(storeRoom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable  UUID id) {
        String message = storeRoomService.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/free")
    public List<StoreRoom> free() {
        return storeRoomService.findFreeStoreRooms();
    }
    
}
