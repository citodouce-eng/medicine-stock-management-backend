package rw.medicine.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.medicine.stock.domain.OrderStatus;
import rw.medicine.stock.domain.PurchaseOrder;
import rw.medicine.stock.service.PurchaseOrderService;

import java.time.LocalDate;
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

    @GetMapping ("/by-status")
    public List<PurchaseOrder> byStatus(@RequestParam OrderStatus status) {
        return purchaseOrderService.findByStatus(status);

    }
    @GetMapping("/between")
    public List<PurchaseOrder> between(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return purchaseOrderService.findBetween(startDate, endDate);
    
    }
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PurchaseOrder purchaseOrder) {
        String message = purchaseOrderService.save(purchaseOrder);
        if (message.equals("Purchase order saved successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
        if (message.equals("Pharmacist already has an open purchase order on that date")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        return ResponseEntity.badRequest().body(message);
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = purchaseOrderService.delete(id);

        if (message.equals("Purchase order deletd successfully")) {
            return ResponseEntity.ok(message);
        }
         if (message.equals("That purchase order has stock entries, delete them first")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
         }
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> changeStatus(@PathVariable UUID id, @RequestParam OrderStatus status) {
        String message = purchaseOrderService.changeStatus(id, status);
        if (message.equals("Purchase order status updated successfully")) {
            return ResponseEntity.ok(message);
        }
        if (message.equals("The purchase order with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return  ResponseEntity.badRequest().body(message);

    }

    
}
