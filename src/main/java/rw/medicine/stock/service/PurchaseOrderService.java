package rw.medicine.stock.service;

import org.springframework.stereotype.Service;

import rw.medicine.stock.domain.OrderStatus;
import rw.medicine.stock.domain.PurchaseOrder;
import rw.medicine.stock.repository.PurchaseOrderRepository;
import rw.medicine.stock.repository.StockEntryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class PurchaseOrderService {
    
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final StockEntryRepository stockEntryRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, StockEntryRepository stockEntryRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.stockEntryRepository = stockEntryRepository;
    }

    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getById(UUID id) {
        return purchaseOrderRepository.findById(id);
    }

    public String save(PurchaseOrder purchaseOrder){
        if (purchaseOrder.getOrderDate() != null && purchaseOrder.getOrderDate().isBefore(LocalDate.now())){
            return "Purchase order date cannot be in the past";
        }
        if (purchaseOrder.getPharmacist() != null) {
            UUID pharmacistId = purchaseOrder.getPharmacist().getId();
            LocalDate date = purchaseOrder.getOrderDate();
            if (purchaseOrderRepository.existsByPharmacistIdAndOrderDateAndStatusNot(pharmacistId, date, OrderStatus.CANCELLED)) {
                return "Pharmacist already has an open purchase order on that date";
            }

        }
        purchaseOrder.setStatus(OrderStatus.DRAFTED);
        purchaseOrderRepository.save(purchaseOrder);
        return "Purchase order saved successfully";
    }

    public String delete(UUID id) {
        if (!purchaseOrderRepository.existsById(id)) {
            return "The purchase oerder with that id does nor exist";

        }
        if (stockEntryRepository.existsByPurchaseOrderId(id)) {
            return "That purchase order has stock entries, delete them first";
        }
          purchaseOrderRepository.deleteById(id);
        return "Purchase order deleted successfully";
    }

    public List<PurchaseOrder> findByStatus(OrderStatus status) {
        return purchaseOrderRepository.findByStatusOrderByOrderDateAsc(status);
    }

    public List<PurchaseOrder> findBetween(LocalDate start, LocalDate end) {
        return purchaseOrderRepository.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }

    public String changeStatus(UUID id, OrderStatus newStatus) {
         Optional<PurchaseOrder> opt = purchaseOrderRepository.findById(id);
         if (opt.isEmpty()) {
            return "The purchase order with that id does not exist";

         }
         PurchaseOrder po = opt.get();
        OrderStatus current = po.getStatus();

        boolean allowed = false;
        if (current == OrderStatus.DRAFTED && (newStatus == OrderStatus.SENT || newStatus == OrderStatus.CANCELLED)) {
            allowed = true;
        }
        if (current == OrderStatus.SENT && (newStatus == OrderStatus.RECEIVED || newStatus == OrderStatus.CANCELLED)) {
            allowed = true;
        }
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
            
        }
        po.setStatus(newStatus);
        purchaseOrderRepository.save(po);
        return "Purchase order status updated successfully";    
    }

}
