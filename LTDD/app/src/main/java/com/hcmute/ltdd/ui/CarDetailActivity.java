package com.hcmute.ltdd.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.graphics.ColorUtils;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.model.response.CarDetailResponse;
import com.hcmute.ltdd.viewmodel.CarViewModel;

public class CarDetailActivity extends AppCompatActivity {
    private CarViewModel carViewModel;
    private ImageView imgCar;
    private TextView txtName, txtRating, txtTrips, txtLocation, txtPrice, txtDescription;
    private Long carId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        ScrollView scrollView = findViewById(R.id.scrollView);
        LinearLayout layoutTopBar = findViewById(R.id.layoutTopBar);
        ImageView imgCar = findViewById(R.id.imgCar);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                int imageHeight = imgCar.getHeight();

                if (scrollY >= imageHeight) {
                    layoutTopBar.setBackgroundColor(Color.WHITE);
                } else {
                    // Tạo hiệu ứng trong suốt mượt mà
                    float alpha = (float) scrollY / (float) imageHeight;
                    int colorWithAlpha = ColorUtils.setAlphaComponent(Color.WHITE, (int) (alpha * 255));
                    layoutTopBar.setBackgroundColor(colorWithAlpha);
                }
            }
        });

        findViewById(R.id.btnRentCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarDetailActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });


        carId = getIntent().getLongExtra("carId", -1);
        if (carId == -1) {
            Toast.makeText(this, "Không tìm thấy xe", Toast.LENGTH_SHORT).show();
            finish();
        }
        initViews();
        initViewModel();
        carViewModel.getCarDetail(this, carId);
        observeViewModel();
    }
    private void initViews() {
        imgCar = findViewById(R.id.imgCar);
        txtName = findViewById(R.id.txtName);
        txtRating = findViewById(R.id.txtRating);
        txtTrips = findViewById(R.id.txtTrips);
        txtLocation = findViewById(R.id.txtLocation);
        txtPrice = findViewById(R.id.txtCarPriceDetail);
        txtDescription = findViewById(R.id.txtDescription);
    }

    private void initViewModel() {
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);
    }

    private void observeViewModel() {
        carViewModel.getCarDetailLiveData().observe(this, carDetail -> {
            if (carDetail != null) {
                updateUI(carDetail);
            }
        });

        carViewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(CarDetailActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        carViewModel.getIsLoading().observe(this, isLoading -> {
            // Show/hide progress bar based on isLoading
        });
    }
    private void updateUI(CarDetailResponse carDetail) {
        Glide.with(this).load(carDetail.getImageUrl()).into(imgCar);
        txtName.setText(carDetail.getName());
        txtRating.setText(String.format("%.1f ★", carDetail.getAvgRating()));
        txtTrips.setText(String.format("%d chuyến", carDetail.getTripCount()));
        txtLocation.setText(carDetail.getLocation());
        txtPrice.setText(String.format("%.0fK/ngày", carDetail.getPrice()));
        txtDescription.setText(carDetail.getDescription());
    }
}
