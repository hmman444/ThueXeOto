package com.hcmute.thuexe.dto.request;

import java.time.LocalTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostRequest {

    @NotNull(message = "Car ID không được để trống")
    private int carId;

    @Min(value = 0, message = "Giá thuê mỗi ngày phải lớn hơn hoặc bằng 0")
    private double pricePerDay;

    @NotNull(message = "Thời gian nhận xe không được để trống")
    private LocalTime pickupTime;

    @NotNull(message = "Thời gian trả xe không được để trống")
    @Future(message = "Thời gian trả xe phải ở tương lai")
    private LocalTime dropoffTime;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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
}