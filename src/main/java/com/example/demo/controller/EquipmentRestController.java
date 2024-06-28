package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Equipment;
import com.example.demo.model.EquipmentStatusReportProjection;
import com.example.demo.model.EquipmentStatusUpdateRequest;
import com.example.demo.repository.EquipmentRepository;
import com.example.demo.repository.EquipmentStatusRepository;
import com.example.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EquipmentRestController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentStatusRepository equipmentStatusRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @PutMapping("/equipment/{id}/status")
    public ResponseEntity<Void> updateEquipmentStatus(@PathVariable Long id, @RequestBody EquipmentStatusUpdateRequest request) {
        equipmentService.updateEquipmentStatus(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/equipment/report")
    public List<EquipmentStatusReportProjection> getEquipmentReport(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "serialNumber", required = false) String serialNumber) {

        if (startDate != null && endDate != null && serialNumber != null) {
            return equipmentStatusRepository.findByDateRangeAndSerialNumber(startDate, endDate, serialNumber);
        } else if (startDate != null && endDate != null) {
            return equipmentStatusRepository.findByDateRange(startDate, endDate);
        } else if (serialNumber != null) {
            return equipmentStatusRepository.findBySerialNumber(serialNumber);
        } else {
            return equipmentStatusRepository.findAllReports();
        }
    }

    @GetMapping("/equipment")
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    @GetMapping("/equipment/search")
    public List<Equipment> searchEquipment(@RequestParam("query") String query) {
        return equipmentRepository.findByNameContaining(query);
    }

    @PostMapping("/equipment")
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @GetMapping("/equipment/{id_equipment}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable(value = "id_equipment") Long equipmentId)
            throws ResourceNotFoundException {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found for this id :: " + equipmentId));
        return ResponseEntity.ok().body(equipment);
    }

    @PutMapping("/equipment/{id_equipment}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable(value = "id_equipment") Long equipmentId,
                                                     @RequestBody Equipment equipmentDetails) throws ResourceNotFoundException {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found for this id :: " + equipmentId));

        equipment.setCategory(equipmentDetails.getCategory());
        equipment.setProduct_line(equipmentDetails.getProduct_line());
        equipment.setName_product(equipmentDetails.getName_product());
        equipment.setSerial_number(equipmentDetails.getSerial_number());
        equipment.setDescription(equipmentDetails.getDescription());
        equipment.setMarket_price(equipmentDetails.getMarket_price());
        equipment.setPurchase_price(equipmentDetails.getPurchase_price());
        equipment.setComment(equipmentDetails.getComment());
        equipment.setStatus(equipmentDetails.getStatus());
        equipment.setLocation(equipmentDetails.getLocation());
        equipment.setName(equipmentDetails.getName());

        final Equipment updatedEquipment = equipmentRepository.save(equipment);
        return ResponseEntity.ok(updatedEquipment);
    }

    @DeleteMapping("/equipment/{id_equipment}")
    public Map<String, Boolean> deleteEquipment(@PathVariable(value = "id_equipment") Long equipmentId)
            throws ResourceNotFoundException {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found for this id :: " + equipmentId));

        equipmentRepository.delete(equipment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
