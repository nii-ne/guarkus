package com.niine.quarkus.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private String color;
    private String brand;

    public Vehicle(String color, String brand) {
        this.color = color;
        this.brand = brand;
    }

    public Vehicle() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

