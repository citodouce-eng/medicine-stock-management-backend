package rw.medicine.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.medicine.stock.domain.MedicineClass;
import rw.medicine.stock.service.MedicineClassService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/medicine-classes")
public class MedicineClassController {
    
    private final MedicineClassService medicineClassService;

    public MedicineClassController(MedicineClassService medicineClassService) {
        this.medicineClassService = medicineClassService;

    }

    @GetMapping 
    public List<MedicineClass> getAll() {
        return medicineClassService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicineClass> getById(@PathVariable UUID id) {
        Optional<MedicineClass> opt = medicineClassService.getById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(opt.get());
    }

    @PostMapping("/save")
    public ResponseEntity<MedicineClass> save(@RequestBody MedicineClass medicineClass) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineClassService.save(medicineClass));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = medicineClassService.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);       
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/unused")
    public List<MedicineClass> unused() {
        return medicineClassService.findUnused();
    }
}
