package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.medicine.stock.domain.MedicineClass;

import java.util.UUID;

public interface MedicineClassRepository extends JpaRepository<MedicineClass, UUID> {

    
} 
