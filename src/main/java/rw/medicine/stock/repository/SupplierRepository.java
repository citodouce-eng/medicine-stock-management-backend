package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.medicine.stock.domain.Supplier;


import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    List<Supplier> findByCompanyNameIgnoreCaseOrderByContactNameAsc(String companyName);

    @Query("SELECT DISTINCT po.supplier FROM PurchaseOrder po WHERE po.pharmacist.id = :pharmacistId")

    List<Supplier> findSuppliersOfPharmacist(@Param("pharmacistId") UUID pharmacistId);

}


