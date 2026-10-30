package rw.medicine.stock.service;

import org.springframework.stereotype.Service;
import rw.medicine.stock.domain.Pharmacist;
import rw.medicine.stock.repository.PharmacistRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class PharmacistService {

    private final PharmacistRepository pharmacistRepository;

    public PharmacistService(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    public List<Pharmacist> getAll() {
        return pharmacistRepository.findAll();
    }
    public Optional<Pharmacist> getById(UUID id) {
        return pharmacistRepository.findById(id);
    }

    public Pharmacist save(Pharmacist pharmacist) {
        return pharmacistRepository.save(pharmacist);
    }
    
    public String delete(UUID id) {
       if (!pharmacistRepository.existsById(id)) {
        return "The pharmacist with that id does not exist";
       } 

       pharmacistRepository.deleteById(id);
       return"Pharmacist deleted successfully";
    }

    public List<Pharmacist> findByMedicineClassName(String name) {
        return pharmacistRepository.findByMedicineClassName(name);
    }
}
