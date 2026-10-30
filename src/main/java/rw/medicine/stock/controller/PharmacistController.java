package rw.medicine.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.medicine.stock.domain.Pharmacist;
import rw.medicine.stock.service.PharmacistService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/pharmacists")
public class PharmacistController {
    
    private final PharmacistService pharmacistService;

    public PharmacistController(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @GetMapping 
    public List<Pharmacist> getAll() {
        return pharmacistService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacist> getById(@PathVariable UUID id) {
        Optional<Pharmacist> opt = pharmacistService.getById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(opt.get());

    }

    @PostMapping("/save")
    public ResponseEntity<Pharmacist> save(@RequestBody Pharmacist pharmacist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pharmacistService.save(pharmacist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = pharmacistService.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/by-medicine-class")
    public List<Pharmacist> byMedicineClass(@RequestParam String name) {
        return pharmacistService.findByMedicineClassName(name);
    }
}
