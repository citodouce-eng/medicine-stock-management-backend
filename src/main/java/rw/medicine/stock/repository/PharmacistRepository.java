package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.medicine.stock.domain.Pharmacist;

import java.util.List;
import java.util.UUID;

public interface PharmacistRepository extends  JpaRepository<Pharmacist, UUID> {
 
@Query("SELECT p FROM Pharmacist p JOIN p.medicineClasses c WHERE LOWER(c.name) = LOWER(:name)")
List<Pharmacist> findByMedicineClassName(@Param("name") String name);

}
