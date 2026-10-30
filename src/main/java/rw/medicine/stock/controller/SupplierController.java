package rw.medicine.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.medicine.stock.domain.Supplier;
import rw.medicine.stock.service.SupplierService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController 
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping 
    public List<Supplier> getAll() {
        return supplierService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable UUID id) {
        Optional<Supplier> opt = supplierService.getById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }    
            return ResponseEntity.ok(opt.get());
        
    }

    @PostMapping("/save")
    public ResponseEntity<Supplier> save(@RequestBody Supplier supplier) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.save(supplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = supplierService.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/by-company-name")
    public List<Supplier> byCompanyName(@RequestParam String companyName) {
        return supplierService.findByCompanyName(companyName);
    }
}
