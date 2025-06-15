package com.hcmute.thuexe.dto.response;

import java.time.LocalDateTime;

public class BookingDetailResponse {

    private Long bookingId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double totalPrice;
    private String pickupLocation;
    private String dropoffLocation;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String cancelReason;
    private Boolean insuranceSelected;
    private Boolean deliverySelected;
    private Boolean driverRequired;

    private int carId;
    private String carName;
    public int getCarId() {
        return carId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }
    private String carImageUrl;
    private Double carPrice;

    // Renter Information
    private Long renterId;
    private String renterName;
    private String renterPhone;
    private String renterImageUrl;

    // Owner Information
    private Long ownerId;
    private String ownerName;
    private String ownerPhone;
    private String ownerImageUrl;
    private ReviewDTO review;
    public ReviewDTO getReview() {
        return review;
    }
    public void setReview(ReviewDTO review) {
        this.review = review;
    }
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getCancelReason() {
        return cancelReason;
    }
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
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
    public Boolean getDriverRequired() {
        return driverRequired;
    }
    public void setDriverRequired(Boolean driverRequired) {
        this.driverRequired = driverRequired;
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
    public Double getCarPrice() {
        return carPrice;
    }
    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }
    public Long getRenterId() {
        return renterId;
    }
    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }
    public String getRenterName() {
        return renterName;
    }
    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }
    public String getRenterPhone() {
        return renterPhone;
    }
    public void setRenterPhone(String renterPhone) {
        this.renterPhone = renterPhone;
    }
    public String getRenterImageUrl() {
        return renterImageUrl;
    }
    public void setRenterImageUrl(String renterImageUrl) {
        this.renterImageUrl = renterImageUrl;
    }
    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOwnerPhone() {
        return ownerPhone;
    }
    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
    public String getOwnerImageUrl() {
        return ownerImageUrl;
    }
    public void setOwnerImageUrl(String ownerImageUrl) {
        this.ownerImageUrl = ownerImageUrl;
    }


}
