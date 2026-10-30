package rw.medicine.stock.repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.medicine.stock.domain.OrderStatus;
import rw.medicine.stock.domain.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {

    List<PurchaseOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);

    List<PurchaseOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);
    
}
