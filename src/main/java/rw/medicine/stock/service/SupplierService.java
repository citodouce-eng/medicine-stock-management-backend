package rw.medicine.stock.service;
import org.springframework.stereotype.Service;
import rw.medicine.stock.domain.Supplier;
import rw.medicine.stock.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getById(UUID id) {
        return supplierRepository.findById(id);
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public String delete(UUID id) {
        if (!supplierRepository.existsById(id)) {

            return "The supplier with that id does not exit";
        }   
        supplierRepository.deleteById(id);
        return "Supplier deleted successfully";

    }

    public List<Supplier> findByCompanyName(String companyName) {
        return supplierRepository.findByCompanyNameIgnoreCaseOrderByContactNameAsc(companyName);

    }
}
