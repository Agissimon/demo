package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/add-equipment")
    public String addEquipment() {
        return "addEquipment";
    }

    @GetMapping("/update-equipment")
    public String updateEquipment() {
        return "updateEquipment";
    }

    @GetMapping("/search-equipment")
    public String searchEquipment() {
        return "searchEquipment";
    }

    @GetMapping("/reports")
    public String reports() {
        return "report";
    }
}
