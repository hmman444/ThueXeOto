package com.hcmute.ltdd.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.graphics.ColorUtils;

import com.bumptech.glide.Glide;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.data.repository.FeatureRepository;
import com.hcmute.ltdd.model.Feature;

import java.util.ArrayList;
import java.util.List;

public class CarDetailActivity extends AppCompatActivity {

    private TextView tvCarName, txtTrips, txtName, txtLocation, txtCarDescription, txtPostDescription,
            tvGear, tvSeats, tvFuel, tvFuelConsumption;
    private ImageView imgCar, backButton;
    private static final String TAG = "CarDetailActivity";
    private GridLayout gridAmenities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        txtLocation = findViewById(R.id.txtLocation);
        ScrollView scrollView = findViewById(R.id.scrollView);
        LinearLayout layoutTopBar = findViewById(R.id.layoutTopBar);

        backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(v -> finish());

        tvCarName = findViewById(R.id.tvCarName);
        imgCar = findViewById(R.id.imgCar);
        txtTrips = findViewById(R.id.txtTrips);
        txtName = findViewById(R.id.txtName);
        txtCarDescription = findViewById(R.id.txtCarDescription);
        txtPostDescription = findViewById(R.id.txtPostDescription);
        tvGear = findViewById(R.id.gearText);
        tvSeats = findViewById(R.id.seatsText);
        tvFuel = findViewById(R.id.fuelText);
        tvFuelConsumption = findViewById(R.id.fuelConsumptionText);
        gridAmenities = findViewById(R.id.gridAmenities);

        Intent intent = getIntent();
        String carName = intent.getStringExtra("carName");
        String carImageUrl = intent.getStringExtra("carImageUrl");
        int carNumberOfRentals = intent.getIntExtra("carNumberOfRentals", 0);
        String carLocation = intent.getStringExtra("carLocation");
        String carDescription = intent.getStringExtra("carDescription");
        String postDescription = intent.getStringExtra("postDescription");
        String carGearType = getIntent().getStringExtra("carGearType");
        int carSeats = getIntent().getIntExtra("carSeats", 0);
        String carFuelType = getIntent().getStringExtra("carFuelType");
        double carEnergyConsumption = getIntent().getDoubleExtra("carEnergyConsumption", 0);
        ArrayList<String> carFeatures = intent.getStringArrayListExtra("carFeatures");

        Log.d(TAG, "Car Name: " + carName);
        Log.d(TAG, "Car Features: " + carFeatures);

        tvCarName.setText(carName);
        txtName.setText(carName);
        txtTrips.setText(carNumberOfRentals + " chuyến");
        txtLocation.setText("Vị trí: " + carLocation);
        txtCarDescription.setText(carDescription);
        txtPostDescription.setText(postDescription);
        tvGear.setText(carGearType);
        tvSeats.setText(carSeats + " chỗ");
        tvFuel.setText(carFuelType);
        tvFuelConsumption.setText(String.format("%.0f L/100km", carEnergyConsumption));

        Glide.with(this).load(carImageUrl).into(imgCar);

        updateCarAmenities(carFeatures);

        findViewById(R.id.btnRentCar).setOnClickListener(v -> {
            Intent bookingIntent = new Intent(CarDetailActivity.this, BookingActivity.class);
            startActivity(bookingIntent);
        });
    }

    private void updateCarAmenities(List<String> carFeatures) {
        gridAmenities.removeAllViews();

        Log.d(TAG, "GridLayout found: " + (gridAmenities != null));
        Log.d(TAG, "carFeatures size: " + (carFeatures != null ? carFeatures.size() : "null"));

        if (carFeatures != null && !carFeatures.isEmpty()) {
            List<Feature> features = FeatureRepository.getFeatures();
            Log.d(TAG, "Features size from repository: " + features.size());

            for (String featureName : carFeatures) {
                boolean featureFound = false;
                for (Feature feature : features) {
                    if (featureName.equals(feature.getName())) {
                        Log.d(TAG, "Inflating feature: " + featureName);

                        LinearLayout amenityLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.item_car_amenities, null);
                        ImageView amenityImage = amenityLayout.findViewById(R.id.amenityImage);
                        TextView amenityText = amenityLayout.findViewById(R.id.amenityText);

                        amenityImage.setImageResource(feature.getIconResId());
                        amenityText.setText(feature.getName());

                        gridAmenities.addView(amenityLayout);
                        Log.d(TAG, "Feature added to GridLayout: " + featureName);
                        featureFound = true;
                        break;
                    }
                }
                if (!featureFound) {
                    Log.d(TAG, "Feature not found in repository: " + featureName);
                }
            }
        } else {
            Log.d(TAG, "carFeatures is empty or null.");
        }
    }
}
