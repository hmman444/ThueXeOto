package com.hcmute.thuexe.dto.response;

public class ReviewDTO {
    private Long reviewId;
    private Long carId;
    private int rating;
    private String comment;
    private String name;
    private String createdAt;
    private String imageUrl;
    private Long bookingId;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public ReviewDTO(Long reviewId, int rating, Long bookingId, String comment, String createdAt, String name, String imageUrl) {
        this.reviewId = reviewId;
        this.carId = carId;
        this.bookingId = bookingId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    
    public ReviewDTO(Long reviewId, Long carId, int rating, String comment, String name, String createdAt,
            String imageUrl) {
        this.reviewId = reviewId;
        this.carId = carId;
        this.rating = rating;
        this.comment = comment;
        this.name = name;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
