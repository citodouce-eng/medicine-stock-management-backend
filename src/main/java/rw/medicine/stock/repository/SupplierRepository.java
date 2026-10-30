package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.medicine.stock.domain.Supplier;

import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    List<Supplier> findByCompanyNameIgnoreCaseOrderByContactNameAsc(String companyName);
}


