package rw.medicine.stock.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import rw.medicine.stock.domain.OrderStatus;
import rw.medicine.stock.domain.PurchaseOrder;
import rw.medicine.stock.domain.StockEntry;
import rw.medicine.stock.repository.PurchaseOrderRepository;
import rw.medicine.stock.repository.StockEntryRepository;

@Service 
public class StockEntryService {

    private final StockEntryRepository stockEntryRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public StockEntryService(StockEntryRepository stockEntryRepository, PurchaseOrderRepository purchaseOrderRepository) {
        this.stockEntryRepository = stockEntryRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }
    public String save(UUID purchaseOrderId, StockEntry entry) {
        Optional<PurchaseOrder> poOpt = purchaseOrderRepository.findById(purchaseOrderId);
        if (poOpt.isEmpty()) {
            return  "The purchase order with that id does not exist";
        }

         PurchaseOrder po = poOpt.get();
        if (po.getStatus() != OrderStatus.RECEIVED) {
            return "Stock entries are only for RECEIVED purchase orders";
        }

        entry.setReceivedDate(LocalDate.now());
        entry.setPurchaseOrder(po);
        stockEntryRepository.save(entry);
        return "Stock entry saved successfully";

    }
    public List<StockEntry> findBySupplierCode(String code) {
        return stockEntryRepository.findBySupplierCode(code);
    }

    public String delete(UUID id) {
        if (!stockEntryRepository.existsById(id)) {
            return "The stock entry with that id does not exist";

        }
        stockEntryRepository.deleteById(id);
        return "Stock entry deleted successfully";
    }
    
}
