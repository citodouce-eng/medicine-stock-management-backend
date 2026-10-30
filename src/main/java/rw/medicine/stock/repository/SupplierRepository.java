package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.medicine.stock.domain.Supplier;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

} 

