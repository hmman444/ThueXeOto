package com.hcmute.ltdd.model.response;

public class UserProfileResponse {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String birthdate;
    private String imageUrl;
    private String role;
    private String createdAt;

    public Long getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getGender() { return gender; }
    public String getBirthdate() { return birthdate; }
    public String getImageUrl() { return imageUrl; }
    public String getRole() { return role; }
    public String getCreatedAt() { return createdAt; }
}
