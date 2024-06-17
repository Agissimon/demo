package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/add-equipment")
    public String addEquipment(Model model) {
        return "add-equipment";
    }

    @GetMapping("/update-equipment")
    public String updateEquipment(Model model) {
        return "update-equipment";
    }

    @GetMapping("/search-equipment")
    public String searchEquipment(Model model) {
        return "search-equipment";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        return "reports";
    }
}
