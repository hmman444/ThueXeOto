package com.hcmute.ltdd.model.request;

public class AddCarRequest {
    private String name;
    private String brand;
    private String description;
    private String gearType;
    private int seats;
    private String fuelType;
    private double energyConsumption;
    private String imageUrl;
    private boolean driverRequired;

    public AddCarRequest(String name, String brand, String description, String gearType, int seats, String fuelType, double energyConsumption, String imageUrl, boolean driverRequired) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.gearType = gearType;
        this.seats = seats;
        this.fuelType = fuelType;
        this.energyConsumption = energyConsumption;
        this.imageUrl = imageUrl;
        this.driverRequired = driverRequired;
    }

    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getDescription() { return description; }
    public String getGearType() { return gearType; }
    public int getSeats() { return seats; }
    public String getFuelType() { return fuelType; }
    public double getEnergyConsumption() { return energyConsumption; }
    public String getImageUrl() { return imageUrl; }
    public boolean isDriverRequired() { return driverRequired; }
}
