package rw.medicine.stock.repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import rw.medicine.stock.domain.OrderStatus;
import rw.medicine.stock.domain.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {

    List<PurchaseOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);

    List<PurchaseOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);
    
     boolean existsByPharmacistIdAndOrderDateAndStatusNot(UUID pharmacistId, LocalDate orderDate, OrderStatus status);
      
     @Query("SELECT p.status, COUNT(p) FROM PurchaseOrder p GROUP BY p.status")
      List<Object[]> countByStatus();

     @Modifying 
     @Transactional 
     @Query("UPDATE PurchaseOrder p SET p.status = :cancelledStatus " +
           "WHERE p.pharmacist.id = :pharmacistId " +
           "AND p.orderDate = :date " +
           "AND p.status <> :receivedStatus")   
           int cancelDay(@Param("pharmacistId") UUID pharmacistId, 
           @Param("date") LocalDate date,
           @Param("cancelledStatus") OrderStatus cancelledStatus,
           @Param("receivedStatus") OrderStatus receivedStatus);
}
