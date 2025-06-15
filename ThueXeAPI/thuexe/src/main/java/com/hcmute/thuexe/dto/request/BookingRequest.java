package com.hcmute.thuexe.dto.request;


import java.time.LocalDateTime;

public class BookingRequest {
    private Long carId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String pickupLocation;
    private String dropoffLocation;
    private boolean insuranceSelected;
    private boolean deliverySelected;
    private Boolean driverRequired;

    public Boolean getDriverRequired() {
        return driverRequired;
    }

    public void setDriverRequired(Boolean driverRequired) {
        this.driverRequired = driverRequired;
    }

    // Getters & Setters
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public boolean isInsuranceSelected() {
        return insuranceSelected;
    }

    public void setInsuranceSelected(boolean insuranceSelected) {
        this.insuranceSelected = insuranceSelected;
    }

    public boolean isDeliverySelected() {
        return deliverySelected;
    }

    public void setDeliverySelected(boolean deliverySelected) {
        this.deliverySelected = deliverySelected;
    }

}
