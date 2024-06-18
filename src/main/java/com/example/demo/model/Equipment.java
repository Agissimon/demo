package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Equipment {

    // Геттеры и сеттеры
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

private String Category;
private String ProductLine;
private String NameProduct;
private String SerialNumber;
private String Description;
private String MarketPrice;
private String PurchasePrice;
private String Comment;
private String status;
}
