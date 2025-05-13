package com.hcmute.thuexe.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class AddCarRequest {
    @NotBlank(message = "Tên xe không được để trống")
    private String name;

    @NotBlank(message = "Thương hiệu xe không được để trống")
    private String brand;

    private String description;

    @NotBlank(message = "Loại hộp số không được để trống")
    private String gearType;

    @Positive(message = "Số ghế phải là số dương")
    private int seats;

    @NotBlank(message = "Loại nhiên liệu không được để trống")
    private String fuelType;

    @Positive(message = "Mức tiêu thụ nhiên liệu phải là số dương")
    private double energyConsumption;

    private List<String> hasEtc;

    private String location;

    private double price;

    private String imageUrl;

    private boolean driverRequired;

    private String status;

    private String createdAt;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDriverRequired() {
        return driverRequired;
    }

    public void setDriverRequired(boolean driverRequired) {
        this.driverRequired = driverRequired;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getHasEtc() {
        return hasEtc;
    }

    public void setHasEtc(List<String> hasEtc) {
        this.hasEtc = hasEtc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
}
