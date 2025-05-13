package com.hcmute.thuexe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CarFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int featureId;

    private String featureName;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public CarFeature() {}

    public CarFeature(String featureName, Car car) {
        this.featureName = featureName;
        this.car = car;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    
}