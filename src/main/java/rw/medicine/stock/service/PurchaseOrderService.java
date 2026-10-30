package rw.medicine.stock.service;

import org.springframework.stereotype.Service;

import rw.medicine.stock.domain.OrderStatus;
import rw.medicine.stock.domain.PurchaseOrder;
import rw.medicine.stock.repository.PurchaseOrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class PurchaseOrderService {
    
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getById(UUID id) {
        return purchaseOrderRepository.findById(id);
    }

    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder); 
    }

    public String delete(UUID id) {
        if (!purchaseOrderRepository.existsById(id)){
            return "The purchase order with that id does not exist" ;
        }
        purchaseOrderRepository.deleteById(id);
        return "Purchase order deleted successfully";
    }
    
    public List<PurchaseOrder> findByStatus(OrderStatus status) {
        return  purchaseOrderRepository.findByStatusOrderByOrderDateAsc(status);
    }

    public List<PurchaseOrder> findBetween(LocalDate start, LocalDate end) {
        return purchaseOrderRepository.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }
}
