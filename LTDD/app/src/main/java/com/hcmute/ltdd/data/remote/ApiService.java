package com.hcmute.ltdd.data.remote;

import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.request.AddCarRequest;
import com.hcmute.ltdd.model.request.EditProfileRequest;
import com.hcmute.ltdd.model.request.ForgotPasswordRequest;
import com.hcmute.ltdd.model.request.LoginRequest;
import com.hcmute.ltdd.model.request.MessageRequest;
import com.hcmute.ltdd.model.request.RegisterRequest;
import com.hcmute.ltdd.model.request.ResetPasswordRequest;
import com.hcmute.ltdd.model.request.VerifyOtpRequest;
import com.hcmute.ltdd.model.response.CarResponse;
import com.hcmute.ltdd.model.response.ConversationResponse;
import com.hcmute.ltdd.model.response.MessageResponse;
import com.hcmute.ltdd.model.response.UserProfileResponse;
import com.hcmute.ltdd.model.response.UserSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/api/user/profile")
    Call<UserProfileResponse> getUserProfile(@Header("Authorization") String token);

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

    @GET("/api/user/conversations")
    Call<List<ConversationResponse>> getConversations(@Header("Authorization") String token);

    @GET("/api/user/messages/{conversationId}")
    Call<List<MessageResponse>> getMessagesByConversation(@Path("conversationId") Long conversationId, @Header("Authorization") String token);

    @POST("/api/user/message")
    Call<MessageResponse> sendMessage(@Body MessageRequest messageRequest, @Header("Authorization") String token);

    @GET("api/user/search")
    Call<List<UserSearchResponse>> searchUsers(@Query("keyword") String keyword, @Header("Authorization") String token);

    @POST("/api/user/edit-profile")
    Call<ApiResponse<String>> editProfile(@Body EditProfileRequest request, @Header("Authorization") String token);

    @POST("/api/user/add-car")
    Call<ApiResponse<String>> addCar(@Body AddCarRequest request, @Header("Authorization") String token);

    @GET("/api/user/my-cars")
    Call<List<CarResponse>> getMyCars(@Header("Authorization") String token);

}
