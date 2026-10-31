package rw.medicine.stock.controller;

import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.medicine.stock.domain.StockEntry;
import rw.medicine.stock.service.StockEntryService;

@RestController 
@RequestMapping("/api/stock-entries")
public class StockEntryController {

    private final StockEntryService stockEntryService;

    public StockEntryController(StockEntryService stockEntryService) {
        this.stockEntryService = stockEntryService; 
    }
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam  UUID purchaseOrderId, @RequestBody StockEntry entry) {
        String message = stockEntryService.save(purchaseOrderId, entry);

        if (message.equals("Stock entry saved successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(message); 
        }

        if (message.equals("The purchase order with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.badRequest().body(message);
    }
    @GetMapping("/supplier/{supplierCode}")
    public List<StockEntry> bySupplier(@PathVariable String supplierCode) {
        return stockEntryService.findBySupplierCode(supplierCode);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = stockEntryService.delete(id);
        if (message.equals("Stock entry deleted successfully")) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }


    
}
