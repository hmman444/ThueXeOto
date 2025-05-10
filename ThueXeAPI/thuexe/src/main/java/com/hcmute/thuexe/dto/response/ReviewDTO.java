package com.hcmute.thuexe.dto.response;

public class ReviewDTO {
    private Long reviewId;
    private Long carId;
    private int rating;
    private String comment;
    private Long userId;
    private String createdAt;

    public ReviewDTO(Long reviewId, Long carId, int rating, String comment, Long userId, String createdAt) {
        this.reviewId = reviewId;
        this.carId = carId;
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
        this.createdAt = createdAt;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
