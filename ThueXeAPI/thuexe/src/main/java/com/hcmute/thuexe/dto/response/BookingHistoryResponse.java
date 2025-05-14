package com.hcmute.thuexe.dto.response;

import java.time.LocalDateTime;

public class BookingHistoryResponse {
    private Long bookingId;
    private String carName;
    private String carImageUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double totalPrice;
    private String pickupLocation;
    private String dropoffLocation;
    private String status;
    private boolean insuranceSelected;
    private boolean deliverySelected;
    private Boolean driverRequired;

    
    public BookingHistoryResponse(Long bookingId, String carName, String carImageUrl, LocalDateTime startDate,
            LocalDateTime endDate, Double totalPrice, String pickupLocation, String dropoffLocation, String status,
            boolean insuranceSelected, boolean deliverySelected, Boolean driverRequired, String cancelReason) {
        this.bookingId = bookingId;
        this.carName = carName;
        this.carImageUrl = carImageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.status = status;
        this.insuranceSelected = insuranceSelected;
        this.deliverySelected = deliverySelected;
        this.driverRequired = driverRequired;
        this.cancelReason = cancelReason;
    }

    public Boolean getDriverRequired() {
        return driverRequired;
    }

    public void setDriverRequired(Boolean driverRequired) {
        this.driverRequired = driverRequired;
    }

    private String cancelReason;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    
}
