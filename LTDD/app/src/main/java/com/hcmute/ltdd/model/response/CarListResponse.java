package com.hcmute.ltdd.model.response;

public class CarListResponse {
    private Long carId;
    private String name;
    private String brand;
    private String gearType;
    private int seats;
    private String fuelType;
    private String location;
    private String imageUrl;
    private double price;
    private Long ownerId;
    private Double avgRating;
    private Long tripCount;

    public Long getCarId() {
        return carId;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getGearType() {
        return gearType;
    }

    public int getSeats() {
        return seats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getLocation() {
        return location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public Long getTripCount() {
        return tripCount;
    }
}
