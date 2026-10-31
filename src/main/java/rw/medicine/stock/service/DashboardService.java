package rw.medicine.stock.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;


import org.springframework.stereotype.Service;

import rw.medicine.stock.domain.OrderStatus;
import rw.medicine.stock.repository.PharmacistRepository;
import rw.medicine.stock.repository.PurchaseOrderRepository;
import rw.medicine.stock.repository.StockEntryRepository;
import rw.medicine.stock.repository.StoreRoomRepository;
import rw.medicine.stock.repository.SupplierRepository;
import java.util.Map;


@Service
public class DashboardService {
    private final SupplierRepository supplierRepo;
    private final PharmacistRepository pharmacistRepo;
    private final StoreRoomRepository storeRoomRepo;
    private final PurchaseOrderRepository purchaseOrderRepo;
    private final StockEntryRepository stockEntryRepo;

    public DashboardService(SupplierRepository supplierRepo, PharmacistRepository pharmacistRepo, StoreRoomRepository storeRoomRepo, PurchaseOrderRepository purchaseOrderRepo, StockEntryRepository stockEntryRepo ) {
        this.supplierRepo = supplierRepo;
        this.pharmacistRepo = pharmacistRepo;
        this.storeRoomRepo = storeRoomRepo;
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.stockEntryRepo = stockEntryRepo;
    }

    public Map<String, Long> summary() {
        Map<String, Long> map = new LinkedHashMap<>();
        map.put("suppliers", supplierRepo.count());
        map.put("pharmacists", pharmacistRepo.count());
        map.put("freeStoreRooms", (long) storeRoomRepo.findFreeStoreRooms().size());
        map.put("ordersToday", purchaseOrderRepo.countByOrderDate(LocalDate.now()));
        map.put("drafted", purchaseOrderRepo.countByStatus(OrderStatus.DRAFTED));
        map.put("stockEntries", stockEntryRepo.count());
        return map;
    }

    
}
