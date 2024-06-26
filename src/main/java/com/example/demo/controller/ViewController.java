package com.example.demo.controller;

import com.example.demo.model.Equipment;
import com.example.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/")
    public String index(Model model) {
        List<Equipment> equipmentList = equipmentService.getAllEquipment();
        model.addAttribute("equipmentList", equipmentList);
        return "index";
    }

    @GetMapping("/addEquipment")
    public String addEquipment() {
        return "addEquipment";
    }

    @GetMapping("/updateEquipment")
    public String updateEquipment() {
        return "updateEquipment";
    }

    @GetMapping("/searchEquipment")
    public String searchEquipment() {
        return "searchEquipment";
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }
}