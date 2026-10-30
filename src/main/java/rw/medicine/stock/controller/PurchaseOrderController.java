package rw.medicine.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.medicine.stock.domain.PurchaseOrder;
import rw.medicine.stock.service.PurchaseOrderService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService ;
    }

    @GetMapping 
    public List<PurchaseOrder> getAll() {
        return purchaseOrderService.getAll();

    }
    @GetMapping ("/{id}")
    public ResponseEntity<PurchaseOrder> getById(@PathVariable UUID id) {
        Optional<PurchaseOrder> opt = purchaseOrderService.getById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(opt.get()) ;
    }

    @PostMapping("/save")
    public ResponseEntity<PurchaseOrder> save(@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.save(purchaseOrder));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = purchaseOrderService.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    
}
