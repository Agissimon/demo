package com.example.demo.repository;

import com.example.demo.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    // Поиск по названию оборудования
    List<Equipment> findByNameContaining(String name);
}
