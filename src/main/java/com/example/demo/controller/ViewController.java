package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
    @GetMapping("/")
    public String index() {
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
