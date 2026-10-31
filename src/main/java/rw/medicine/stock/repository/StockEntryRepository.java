package rw.medicine.stock.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.medicine.stock.domain.StockEntry;

public interface StockEntryRepository extends JpaRepository<StockEntry, UUID> {
    boolean existsByPurchaseOrderId(UUID purchaseOrderId);

    @Query("SELECT s FROM StockEntry s WHERE s.purchaseOrder.supplier.supplierCode = :code ORDER BY s.receivedDate DESC")

List<StockEntry> findBySupplierCode(@Param("code")String code);    
}
