package rw.medicine.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.medicine.stock.domain.Pharmacist;

import java.util.UUID;

public interface PharmacistRepository extends  JpaRepository<Pharmacist, UUID> {

} 
