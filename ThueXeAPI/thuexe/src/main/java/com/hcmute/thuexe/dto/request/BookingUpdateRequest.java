package com.hcmute.thuexe.dto.request;

import java.time.LocalDateTime;

public class BookingUpdateRequest {
    private Long bookingId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String pickupLocation;
    private String dropoffLocation;
    private Boolean insuranceSelected;
    private Boolean deliverySelected;
    private Boolean driverRequired;

    public Boolean getDriverRequired() {
        return driverRequired;
    }

    public void setDriverRequired(Boolean driverRequired) {
        this.driverRequired = driverRequired;
    }

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public Boolean getInsuranceSelected() {
        return insuranceSelected;
    }

    public void setInsuranceSelected(Boolean insuranceSelected) {
        this.insuranceSelected = insuranceSelected;
    }

    public Boolean getDeliverySelected() {
        return deliverySelected;
    }

    public void setDeliverySelected(Boolean deliverySelected) {
        this.deliverySelected = deliverySelected;
    }

}
