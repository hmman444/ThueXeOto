package com.hcmute.thuexe.dto.response;

public class UserProfileResponse {
    private Long userId;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String gender;
    private String birthdate;
    private String imageUrl;
    private String role;
    private String createdAt;

    // Default constructor
    public UserProfileResponse() {
    }

    // All arguments constructor
    public UserProfileResponse(Long userId, String name, String email, String address, String phone,
                                String gender, String birthdate, String imageUrl, String role, String createdAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.imageUrl = imageUrl;
        this.role = role;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
