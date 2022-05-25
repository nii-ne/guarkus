package com.niine.quarkus.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleRequest {
    @NotBlank(message = "Color is required")
    private String color;
    @NotBlank(message = "Brand is required")
    private String brand;
    @Min(value = 0, message = "Total must be greater than or equal 0")
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
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
