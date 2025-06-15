package com.hcmute.thuexe.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "carId")
    private Car car;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCancelReason() {
        return cancelReason;
    }
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double totalPrice;
    private String pickupLocation;
    private String dropoffLocation;
    private String status; // pending, confirmed, cancelled, completed
    private LocalDateTime createdAt;
    private boolean insuranceSelected;
    private boolean deliverySelected;
    private Boolean driverRequired;
    public Boolean getDriverRequired() {
        return driverRequired;
    }

    public void setDriverRequired(Boolean driverRequired) {
        this.driverRequired = driverRequired;
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

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "cancel_reason")
    private String cancelReason;

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }


}
