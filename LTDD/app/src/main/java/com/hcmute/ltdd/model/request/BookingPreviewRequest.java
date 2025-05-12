package com.hcmute.ltdd.model.request;

import java.io.Serializable;

public class BookingPreviewRequest implements Serializable {
    private Long carId;
    private String startDate;
    private String endDate;
    private String pickupLocation;
    private String dropoffLocation;
    private boolean insuranceSelected;
    private boolean deliverySelected;
    private boolean driverRequired;

    public BookingPreviewRequest() {
    }

    public BookingPreviewRequest(Long carId, String startDate, String endDate, String pickupLocation, String dropoffLocation,
                                 boolean insuranceSelected, boolean deliverySelected, boolean driverRequired) {
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.insuranceSelected = insuranceSelected;
        this.deliverySelected = deliverySelected;
        this.driverRequired = driverRequired;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public boolean isDriverRequired() {
        return driverRequired;
    }

    public void setDriverRequired(boolean driverRequired) {
        this.driverRequired = driverRequired;
    }
}
