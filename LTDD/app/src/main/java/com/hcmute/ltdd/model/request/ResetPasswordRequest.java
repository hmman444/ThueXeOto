package com.hcmute.ltdd.model.request;

public class ResetPasswordRequest {
    private String email;
    private String otpCode;
    private String newPassword;

    public ResetPasswordRequest(String email, String otpCode, String newPassword) {
        this.email = email;
        this.otpCode = otpCode;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public String getNewPassword() {
        return newPassword;
    }
}