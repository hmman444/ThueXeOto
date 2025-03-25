package com.hcmute.ltdd.model;

public class Car {
    private String imageUrl;
    private String name;
    private String gearType;
    private int seats;
    private String fuelType;
    private String location;
    private double rating;
    private int trips;
    private String price;
    private int requests;

    public Car(String imageUrl, String name, String gearType, int seats, String fuelType,
                   String location, double rating, int trips, String price, int requests) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.gearType = gearType;
        this.seats = seats;
        this.fuelType = fuelType;
        this.location = location;
        this.rating = rating;
        this.trips = trips;
        this.price = price;
        this.requests = requests;
    }

    public String getImageUrl() { return imageUrl; }
    public String getName() { return name; }
    public String getGearType() { return gearType; }
    public int getSeats() { return seats; }
    public String getFuelType() { return fuelType; }
    public String getLocation() { return location; }
    public double getRating() { return rating; }
    public int getTrips() { return trips; }
    public String getPrice() { return price; }
    public int getRequests() { return requests; }
}
