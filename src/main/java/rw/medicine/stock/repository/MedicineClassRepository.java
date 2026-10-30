package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.medicine.stock.domain.MedicineClass;

import java.util.List;
import java.util.UUID;

public interface MedicineClassRepository extends JpaRepository<MedicineClass, UUID> {

    @Query("SELECT m FROM MedicineClass m WHERE m.pharmacists IS EMPTY")
    List<MedicineClass> findUnusedMedicineClasses();
    
} 
