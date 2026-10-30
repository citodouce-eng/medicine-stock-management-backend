package rw.medicine.stock.service;

import org.springframework.stereotype.Service;
import rw.medicine.stock.domain.MedicineClass;
import rw.medicine.stock.repository.MedicineClassRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service 
public class MedicineClassService {
    private final MedicineClassRepository medicineClassRepository;

    public MedicineClassService(MedicineClassRepository medicineClassRepository) {
        this.medicineClassRepository = medicineClassRepository;
    }

    public List<MedicineClass> getAll() {
        return medicineClassRepository.findAll();
    }

    public Optional<MedicineClass> getById(UUID id) {
        return medicineClassRepository.findById(id);
    }

    public MedicineClass save(MedicineClass medicineClass) {
        return medicineClassRepository.save(medicineClass);
    }

    public String delete(UUID id) {
        if (!medicineClassRepository.existsById(id)) {
            return "The medicine class with that id does not exist";
        }
        medicineClassRepository.deleteById(id);
        return "Medicine class deleted successfully";
        }
    }
    

