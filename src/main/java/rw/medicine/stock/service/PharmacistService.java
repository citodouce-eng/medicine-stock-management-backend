package rw.medicine.stock.service;

import org.springframework.stereotype.Service;

import rw.medicine.stock.domain.MedicineClass;
import rw.medicine.stock.domain.Pharmacist;
import rw.medicine.stock.domain.StoreRoom;
import rw.medicine.stock.repository.MedicineClassRepository;
import rw.medicine.stock.repository.PharmacistRepository;
import rw.medicine.stock.repository.StoreRoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class PharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final StoreRoomRepository storeRoomRepository;
    private final MedicineClassRepository medicineClassRepository;
     
    public PharmacistService(PharmacistRepository pharmacistRepository, StoreRoomRepository storeRoomRepository, MedicineClassRepository medicineClassRepository) {
        this.pharmacistRepository = pharmacistRepository;
        this.storeRoomRepository = storeRoomRepository;
        this.medicineClassRepository = medicineClassRepository;
        
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

    public String addMedicineClass(UUID pharmacistId, UUID medicineClassId) {
        Optional<Pharmacist> pharmacistOpt = pharmacistRepository.findById(pharmacistId);
        if (pharmacistOpt.isEmpty()) {
            return "The pharmacist with that id does not exist";
        }
        Optional<MedicineClass> classOpt = medicineClassRepository.findById(medicineClassId);
        if (classOpt.isEmpty()) {
            return "That medicine class does not exist";
        }

        Pharmacist pharmacist = pharmacistOpt.get();
        MedicineClass medicineClass = classOpt.get();

        if (pharmacist.getMedicineClasses().contains(medicineClass)) {
            return "The pharmacist already has that medicine class";
        }

        pharmacist.getMedicineClasses().add(medicineClass);
        pharmacistRepository.save(pharmacist);
        return "Medicine class added to the pharmacist";
    }

    public String changeStoreRoom(UUID pharmacistId, int roomNumber) {
        Optional<Pharmacist> pharmacistOpt = pharmacistRepository.findById(pharmacistId);
        if (pharmacistOpt.isEmpty()) {
            return "The pharmacist with that id does not exist";
        }
        Optional<StoreRoom> roomOpt = storeRoomRepository.findByRoomNumber(roomNumber);
        if (roomOpt.isEmpty()) {
            return "The store room with that roomNumber does not exist"; 
        }

        StoreRoom newRoom = roomOpt.get();
        if (storeRoomRepository.existsByRoomNumberAndPharmacistsIsNotEmpty(roomNumber)){
            return "That store room is taken by another pharmacist";
        }
        Pharmacist p = pharmacistOpt.get();
        p.setStoreRoom(newRoom);
        pharmacistRepository.save(p);
        
        return"Store room changed successfully";

    }
}
