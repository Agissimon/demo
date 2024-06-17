package com.example.demo.controller;

import com.example.demo.model.Equipment;
import com.example.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/add-equipment")
    public String addEquipmentForm(Model model) {
        model.addAttribute("equipment", new Equipment());
        return "add-equipment";
    }

    @PostMapping("/add-equipment")
    public String addEquipment(@ModelAttribute Equipment equipment) {
        equipmentService.addEquipment(equipment);
        return "redirect:/";
    }

    @GetMapping("/update-equipment/{id}")
    public String updateEquipmentForm(@PathVariable Long id, Model model) {
        Equipment equipment = equipmentService.getAllEquipment().stream()
                .filter(eq -> eq.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("equipment", equipment);
        return "update-equipment";
    }

    @PostMapping("/update-equipment")
    public String updateEquipment(@ModelAttribute Equipment equipment) {
        equipmentService.updateEquipment(equipment);
        return "redirect:/";
    }

    @GetMapping("/search-equipment")
    public String searchEquipment(@RequestParam String query, Model model) {
        model.addAttribute("equipments", equipmentService.searchEquipment(query));
        return "search-equipment";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("equipments", equipmentService.getAllEquipment());
        return "reports";
    }
}
