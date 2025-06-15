package com.hcmute.ltdd.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.response.CarDetailResponse;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarViewModel extends ViewModel {
    private MutableLiveData<CarDetailResponse> carDetailLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MutableLiveData<CarDetailResponse> getCarDetailLiveData() {
        return carDetailLiveData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void getCarDetail(Context context, Long carId) {
        isLoading.setValue(true);
        ApiService apiService = RetrofitClient.getRetrofit(context).create(ApiService.class);
        apiService.getCarDetail(carId).enqueue(new Callback<ApiResponse<CarDetailResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<CarDetailResponse>> call, Response<ApiResponse<CarDetailResponse>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<CarDetailResponse> apiResponse = response.body();


                    Log.d("CarViewModel", "API Response Success: " + new Gson().toJson(apiResponse));
                    Log.d("CarViewModel", "API Response JSON: " + new Gson().toJson(response.body()));

                    if (response.body().isSuccess()) {
                        carDetailLiveData.setValue(response.body().getData());
                    } else {
                        errorMessage.setValue("API Error: " + apiResponse.getMessage());
                        Log.e("CarViewModel", "API Error Message: " + apiResponse.getMessage());
                    }
                } else {
                    errorMessage.setValue("Lỗi khi tải dữ liệu");
                    Log.e("CarViewModel", "API Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CarDetailResponse>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Lỗi kết nối");
                Log.e("CarViewModel", t.getMessage());
            }
        });
    }
}
