package com.hcmute.thuexe.dto.response;

import java.time.LocalTime;
import java.util.List;

public class PostResponse {
    private int postId;
    private int carId;
    private Long userId;
    private double pricePerDay;
    private LocalTime pickupTime;
    private LocalTime dropoffTime;
    private String description;

    // Các thuộc tính từ Car
    private String carName;
    private String carBrand;
    private String carDescription;
    private String carGearType;
    private int carSeats;
    private String carFuelType;
    private double carEnergyConsumption;
    private String carImageUrl;
    private boolean carDriverRequired;
    private int carNumberOfRentals;
    private List<String> features;

    private String createdAt;

    public PostResponse(int postId, int carId, Long userId, double pricePerDay, LocalTime pickupTime,
                        LocalTime dropoffTime, String description, String carName, String carBrand,
                        String carDescription, String carGearType, int carSeats, String carFuelType, 
                        double carEnergyConsumption, String carImageUrl, boolean carDriverRequired, 
                        int carNumberOfRentals, List<String> features, String createdAt) {
        this.postId = postId;
        this.carId = carId;
        this.userId = userId;
        this.pricePerDay = pricePerDay;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
        this.description = description;
        this.carName = carName;
        this.carBrand = carBrand;
        this.carDescription = carDescription;
        this.carGearType = carGearType;
        this.carSeats = carSeats;
        this.carFuelType = carFuelType;
        this.carEnergyConsumption = carEnergyConsumption;
        this.carImageUrl = carImageUrl;
        this.carDriverRequired = carDriverRequired;
        this.carNumberOfRentals = carNumberOfRentals;
        this.features = features; // Gán danh sách tính năng vào
        this.createdAt = createdAt;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocalTime getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(LocalTime dropoffTime) {
        this.dropoffTime = dropoffTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public String getCarGearType() {
        return carGearType;
    }

    public void setCarGearType(String carGearType) {
        this.carGearType = carGearType;
    }

    public int getCarSeats() {
        return carSeats;
    }

    public void setCarSeats(int carSeats) {
        this.carSeats = carSeats;
    }

    public String getCarFuelType() {
        return carFuelType;
    }

    public void setCarFuelType(String carFuelType) {
        this.carFuelType = carFuelType;
    }

    public double getCarEnergyConsumption() {
        return carEnergyConsumption;
    }

    public void setCarEnergyConsumption(double carEnergyConsumption) {
        this.carEnergyConsumption = carEnergyConsumption;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    public boolean isCarDriverRequired() {
        return carDriverRequired;
    }

    public void setCarDriverRequired(boolean carDriverRequired) {
        this.carDriverRequired = carDriverRequired;
    }

    public int getCarNumberOfRentals() {
        return carNumberOfRentals;
    }

    public void setCarNumberOfRentals(int carNumberOfRentals) {
        this.carNumberOfRentals = carNumberOfRentals;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    
}
