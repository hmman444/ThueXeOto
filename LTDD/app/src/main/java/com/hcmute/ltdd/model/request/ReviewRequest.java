package com.hcmute.ltdd.model.request;

public class ReviewRequest {
    private int carId;
    private Integer rating;
    private String comment;

    public ReviewRequest(int carId, Integer rating, String comment) {
        this.carId = carId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
