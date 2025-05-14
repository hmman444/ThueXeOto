package com.hcmute.thuexe.dto.response;

import java.util.List;

public class CarResponse {
    private Long carId;
    private String name;
    private String brand;
    private String description;
    private String gearType;
    private int seats;
    private String fuelType;
    private double energyConsumption;
    private List<String> hasEtc;
    private String location;
    private double price;
    private String imageUrl;
    private String status;
    private boolean driverRequired;
    private String createdAt;
    
    public CarResponse() {}

    public CarResponse(Long carId, String name, String brand, String description, String gearType, int seats, 
                        String fuelType, double energyConsumption, List<String> hasEtc, String location, double price,
                        String imageUrl, String status, boolean driverRequired, String createdAt) {
        this.carId = carId;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.gearType = gearType;
        this.seats = seats;
        this.fuelType = fuelType;
        this.energyConsumption = energyConsumption;
        this.hasEtc = hasEtc;
        this.location = location;
        this.price = price;
        this.imageUrl = imageUrl;
        this.status = status;
        this.driverRequired = driverRequired;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDriverRequired() {
        return driverRequired;
    }

    public void setDriverRequired(boolean driverRequired) {
        this.driverRequired = driverRequired;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
