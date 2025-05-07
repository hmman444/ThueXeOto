package com.hcmute.ltdd.model.response;

public class CarDetailResponse {
    private Long carId;
    private String name;
    private String imageUrl;
    private String description;
    private String location;
    private Double price;
    private Double avgRating;
    private Long tripCount;

    public CarDetailResponse(Long carId, String name, String imageUrl, String description, String location, Double price, Double avgRating, Long tripCount) {
        this.carId = carId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.location = location;
        this.price = price;
        this.avgRating = avgRating;
        this.tripCount = tripCount;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Long getTripCount() {
        return tripCount;
    }

    public void setTripCount(Long tripCount) {
        this.tripCount = tripCount;
    }
}
