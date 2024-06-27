package com.example.demo.service;

//import com.example.demo.model.EquipmentStatus;
import com.example.demo.model.Equipment;
import com.example.demo.repository.EquipmentRepository;
//import com.example.demo.repository.EquipmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired

//rivate EquipmentStatusRepository equipmentStatusRepository;
//
//ublic List<EquipmentStatus> findEquipmentStatusByDate(Date date) {
//   return equipmentStatusRepository.findAllByDate(date);
//

    private EquipmentRepository equipmentRepository;

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
