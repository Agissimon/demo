package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "equipmentstatus")
public class EquipmentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipment_status")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_equipment")
    private Equipment equipment;

    @Column(name = "current_status")
    private String currentStatus;

    @Column(name = "location")
    private String location;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "id_responsible_company")
    private Long idResponsibleCompany;

    @Column(name = "id_responsible_customer_company")
    private Long idResponsibleCustomerCompany;

    // Getters and Setters
}
