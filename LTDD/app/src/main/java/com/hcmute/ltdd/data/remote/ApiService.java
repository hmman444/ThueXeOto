package com.hcmute.ltdd.data.remote;
import com.hcmute.ltdd.model.User;
import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.request.BookingPreviewRequest;
import com.hcmute.ltdd.model.request.ForgotPasswordRequest;
import com.hcmute.ltdd.model.request.LoginRequest;
import com.hcmute.ltdd.model.request.MessageRequest;
import com.hcmute.ltdd.model.request.RegisterRequest;
import com.hcmute.ltdd.model.request.ResetPasswordRequest;
import com.hcmute.ltdd.model.request.VerifyOtpRequest;
import com.hcmute.ltdd.model.response.ConversationResponse;
import com.hcmute.ltdd.model.response.MessageResponse;
import com.hcmute.ltdd.model.response.UserProfileResponse;
import com.hcmute.ltdd.model.response.UserSearchResponse;
import com.hcmute.ltdd.model.request.SearchCarRequest;
import com.hcmute.ltdd.model.response.CarDetailResponse;
import com.hcmute.ltdd.model.response.CarListResponse;
import com.hcmute.ltdd.model.response.BookingPreviewResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/api/user/profile")
    Call<ApiResponse<UserProfileResponse>> getUserProfile(@Header("Authorization") String token);

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
    Call<ApiResponse<List<ConversationResponse>>> getConversations(@Header("Authorization") String token);

    @GET("/api/user/messages/{conversationId}")
    Call<ApiResponse<List<MessageResponse>>> getMessagesByConversation(
            @Path("conversationId") Long conversationId,
            @Header("Authorization") String token
    );

    @POST("/api/user/message")
    Call<ApiResponse<MessageResponse>> sendMessage(
            @Body MessageRequest messageRequest,
            @Header("Authorization") String token
    );

    @GET("/api/user/search")
    Call<ApiResponse<List<UserSearchResponse>>> searchUsers(
            @Query("keyword") String keyword,
            @Header("Authorization") String token
    );

    @POST("/api/user/cars/list")
    Call<ApiResponse<List<CarListResponse>>> searchCars(@Body SearchCarRequest request);

    @GET("/api/user/car/{id}")
    Call<ApiResponse<CarDetailResponse>> getCarDetail(@Path("id") Long carId);

    @POST("/api/user/booking/preview")
    Call<ApiResponse<BookingPreviewResponse>> bookingPreview(@Body BookingPreviewRequest request);
}
