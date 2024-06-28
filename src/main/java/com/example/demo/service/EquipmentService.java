package com.example.demo.service;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Equipment;
import com.example.demo.model.EquipmentStatusUpdateRequest;
import com.example.demo.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired

    private EquipmentRepository equipmentRepository;

    public void updateEquipmentStatus(Long id, EquipmentStatusUpdateRequest request) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found for this id :: " + id));

        equipment.setStatus(request.getCurrent_status());
        equipmentRepository.save(equipment);
    }

    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> searchEquipment(String query) {
        return equipmentRepository.findByNameContaining(query);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    public void deleteEquipment(Equipment equipment) {
        equipmentRepository.delete(equipment);
    }
}
