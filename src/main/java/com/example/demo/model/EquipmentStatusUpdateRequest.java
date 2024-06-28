package com.example.demo.model;

public class EquipmentStatusUpdateRequest {
    private Long id_equipment_status;
    private String location;
    private Long id_equipment;
    private String date_start;
    private String date_end;
    private String current_status;
    private Long id_responsible_company;
    private Long id_responsible_customer_company;

    // Геттеры и сеттеры
    public Long getId_equipment_status() {
        return id_equipment_status;
    }

    public void setId_equipment_status(Long id_equipment_status) {
        this.id_equipment_status = id_equipment_status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId_equipment() {
        return id_equipment;
    }

    public void setId_equipment(Long id_equipment) {
        this.id_equipment = id_equipment;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public Long getId_responsible_company() {
        return id_responsible_company;
    }

    public void setId_responsible_company(Long id_responsible_company) {
        this.id_responsible_company = id_responsible_company;
    }

    public Long getId_responsible_customer_company() {
        return id_responsible_customer_company;
    }

    public void setId_responsible_customer_company(Long id_responsible_customer_company) {
        this.id_responsible_customer_company = id_responsible_customer_company;
    }
}
