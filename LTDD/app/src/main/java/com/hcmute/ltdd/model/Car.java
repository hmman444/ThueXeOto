package com.hcmute.ltdd.model;

import java.util.Date;

public class Car {
    private int carId;
    private int ownerId;
    private String name;
    private String description;
    private String gearType; // "Manual" or "Automatic"
    private int seats;
    private String fuelType; // "Gasoline", "Diesel", "EV"
    private int energyConsumption;
    private String[] hasEtc;
    private String location;
    private double price;
    private String imageUrl;
    private String status;
    private boolean driverRequired;
    private Date createdAt;

    public Car(int carId, int ownerId, String name, String description, String gearType,
               int seats, String fuelType, int energyConsumption, String[] hasEtc, String location,
               double price, String imageUrl, String status, boolean driverRequired,
               Date createdAt) {
        this.carId = carId;
        this.ownerId = ownerId;
        this.name = name;
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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public String[] getHasEtc() {
        return hasEtc;
    }

    public void setHasEtc(String[] hasEtc) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
