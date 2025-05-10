package com.hcmute.ltdd.model.response;

import java.util.List;

public class PostResponse {
    private int postId;
    private int carId;
    private Long userId;
    private double pricePerDay;
    private String pickupTime;
    private String dropoffTime;
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
    private List<String> carFeatures;

    private String createdAt;

    public PostResponse(int postId, int carId, Long userId, double pricePerDay, String pickupTime,
                        String dropoffTime, String description, String carName, String carBrand,
                        String carDescription, String carGearType, int carSeats, String carFuelType,
                        double carEnergyConsumption, String carImageUrl, boolean carDriverRequired,
                        int carNumberOfRentals, List<String> carFeatures, String createdAt) {
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
        this.carFeatures = carFeatures;
        this.createdAt = createdAt;
    }

    // Getters and Setters
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

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(String dropoffTime) {
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

    public List<String> getCarFeatures() {
        return carFeatures;
    }

    public void setCarFeatures(List<String> carFeatures) {
        this.carFeatures = carFeatures;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
