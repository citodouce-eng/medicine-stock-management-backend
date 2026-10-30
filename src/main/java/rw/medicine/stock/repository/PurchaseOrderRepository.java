package rw.medicine.stock.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.medicine.stock.domain.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {

    
}
