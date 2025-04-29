package com.hcmute.ltdd.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.adapter.CarAdapter;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.Car;
import com.hcmute.ltdd.model.request.SearchCarRequest;
import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.response.CarListResponse;
import com.hcmute.ltdd.ui.fragments.FilterBottomSheet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarListActivity extends AppCompatActivity {

    private AutoCompleteTextView actvDistrict;
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private ProgressBar progressBar;
    private List<CarListResponse> carList = new ArrayList<>();
    private ImageButton btnFilter;
    private ApiService apiService = RetrofitClient.getRetrofit(this).create(ApiService.class);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        initViews();
        setupAutoCompleteDistrict();
        setupRecyclerView();
        setupEvents();
    }

    private void initViews() {
        actvDistrict = findViewById(R.id.actv_district);
        progressBar = findViewById(R.id.progress_loading);
        recyclerView = findViewById(R.id.rc_listCar);
        btnFilter = findViewById(R.id.btn_filter);
    }

    private void setupAutoCompleteDistrict() {
        String[] districts = {"Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận 9",
                "Quận 10", "Quận 11", "Quận 12", "Bình Thạnh", "Gò Vấp", "Phú Nhuận", "Tân Bình", "Tân Phú", "Thủ Đức"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, districts);
        actvDistrict.setAdapter(adapter);
        actvDistrict.setOnClickListener(v -> actvDistrict.showDropDown());
        actvDistrict.setText(districts[0], true);

        SearchCarRequest initRequest = new SearchCarRequest();
        initRequest.setLocation(districts[0]);
        searchCars(initRequest);

        actvDistrict.setOnItemClickListener((parent, view, position, id) -> {
            String selectedDistrict = (String) parent.getItemAtPosition(position);

            SearchCarRequest request = new SearchCarRequest();
            request.setLocation(selectedDistrict);

            searchCars(request);
        });
    }

    private void setupRecyclerView() {
        carAdapter = new CarAdapter(this, carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carAdapter);
    }

    private void setupEvents() {
        btnFilter.setOnClickListener(v -> openFilterBottomSheet());
    }

    private void openFilterBottomSheet() {
        FilterBottomSheet filterBottomSheet = new FilterBottomSheet();
        filterBottomSheet.setFilterListener(request -> {
            String location = actvDistrict.getText().toString();
            request.setLocation(location);

            searchCars(request);
        });
        filterBottomSheet.show(getSupportFragmentManager(), filterBottomSheet.getTag());
    }

    private void searchCars(SearchCarRequest request) {
        showLoading(true);
        Gson gson = new Gson();
        Log.d("SearchCarRequest", gson.toJson(request));
        apiService.searchCars(request).enqueue(new Callback<ApiResponse<List<CarListResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<CarListResponse>>> call, Response<ApiResponse<List<CarListResponse>>> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    carList.clear();
                    carList.addAll(response.body().getData());
                    carAdapter.notifyDataSetChanged();
                } else {
                    carList.clear();
                    Toast.makeText(CarListActivity.this, "Không tìm thấy xe phù hợp", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<CarListResponse>>> call, Throwable t) {
                showLoading(false);
                Toast.makeText(CarListActivity.this, "Lỗi mạng hoặc server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }
}
