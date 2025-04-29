package com.hcmute.ltdd.data.remote;

import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.User;
import com.hcmute.ltdd.model.request.SearchCarRequest;
import com.hcmute.ltdd.model.response.CarListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/api/auth/login")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @POST("/api/user/cars/list")
    Call<ApiResponse<List<CarListResponse>>> searchCars(@Body SearchCarRequest request);

}
