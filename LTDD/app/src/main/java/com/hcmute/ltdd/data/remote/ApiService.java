package com.hcmute.ltdd.data.remote;

import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.request.ForgotPasswordRequest;
import com.hcmute.ltdd.model.request.LoginRequest;
import com.hcmute.ltdd.model.request.RegisterRequest;
import com.hcmute.ltdd.model.request.ResetPasswordRequest;
import com.hcmute.ltdd.model.request.VerifyOtpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/auth/register")
    Call<ApiResponse<String>> register(@Body RegisterRequest request);

    @POST("/api/auth/login")
    Call<ApiResponse<String>> login(@Body LoginRequest request);

    @POST("/api/auth/forgot-password")
    Call<ApiResponse<String>> forgotPassword(@Body ForgotPasswordRequest request);

    @POST("/api/auth/verify-otp")
    Call<ApiResponse<String>> verifyOtp(@Body VerifyOtpRequest request);

    @POST("/api/auth/reset-password")
    Call<ApiResponse<String>> resetPassword(@Body ResetPasswordRequest request);
}
