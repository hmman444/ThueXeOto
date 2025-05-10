package com.hcmute.ltdd.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.model.response.CarDetailResponse;
import com.hcmute.ltdd.ui.fragments.DateRangePickerBottomSheet;
import com.hcmute.ltdd.ui.fragments.LocationBottomSheet;
import com.hcmute.ltdd.viewmodel.CarViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.graphics.ColorUtils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class CarDetailActivity extends AppCompatActivity {
    private CarViewModel carViewModel;
    private ImageView imgCar;
    private TextView txtName, txtRating, txtOwnerName, tvCarName,
            txtOwnerTrips, txtOwnerRating, txtTrips, txtPickupLocation,
            txtLocation, txtPrice, txtDescription, txtDropoffLocation,
            txtGearType, txtSeats, txtFuelType, txtEnergyConsumption;
    private LinearLayout pickupLocationLinearLayout;
    private LinearLayout dropoffLocationLinearLayout;
    private LinearLayout startTimeLinearLayout, endTimeLinearLayout;
    private TextView txtPickupTime, txtReturnTime;
    private Long carId;
    private Calendar selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        initViews();
        initViewModel();

        // Handle ScrollView background color change
        setupScrollViewBehavior();

        // Handle button clicks
        setupListeners();

        // Get Car ID from Intent
        carId = getIntent().getLongExtra("carId", -1);
        if (carId == -1) {
            Toast.makeText(this, "Không tìm thấy xe", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Fetch Car Detail
        carViewModel.getCarDetail(this, carId);
        observeViewModel();
    }

    private void setupListeners() {
        startTimeLinearLayout.setOnClickListener(v -> openDateRangePicker(true));
        endTimeLinearLayout.setOnClickListener(v -> openDateRangePicker(false));
        pickupLocationLinearLayout.setOnClickListener(v -> openLocationBottomSheet(true));
        dropoffLocationLinearLayout.setOnClickListener(v -> openLocationBottomSheet(false));
        findViewById(R.id.btnRentCar_cardetail).setOnClickListener(v -> openBookingActivity());
    }


    private void openDateRangePicker(boolean isPickup) {
        DateRangePickerBottomSheet dateRangePicker = new DateRangePickerBottomSheet();
        dateRangePicker.setDateRangePickerListener((selectedDate, selectedTime) -> {
            String dayOfWeek = new java.text.SimpleDateFormat("EEEE", new Locale("vi", "VN")).format(selectedDate.getTime());
            String formattedDate = new java.text.SimpleDateFormat("dd/MM", Locale.getDefault()).format(selectedDate.getTime());
            String summary = String.format("%s, ngày %s, %s", dayOfWeek, formattedDate, selectedTime);

            if (isPickup) {
                txtPickupTime.setText(summary);
            } else {
                txtReturnTime.setText(summary);
            }
        });

        dateRangePicker.show(getSupportFragmentManager(), dateRangePicker.getTag());
    }


    private void openBookingActivity() {
        Intent intent = new Intent(CarDetailActivity.this, BookingActivity.class);
        startActivity(intent);
    }

    private void openLocationBottomSheet(boolean isPickup) {
        LocationBottomSheet locationBottomSheet = new LocationBottomSheet();
        locationBottomSheet.setLocationListener(location -> {
            if (isPickup) {
                txtPickupLocation.setText(location);
            } else {
                txtDropoffLocation.setText(location);
            }
        });
        locationBottomSheet.show(getSupportFragmentManager(), locationBottomSheet.getTag());
    }

    private void initViews() {
        imgCar = findViewById(R.id.imgCar_cardetail);
        txtName = findViewById(R.id.txtName_cardetail);
        txtRating = findViewById(R.id.txtRating_cardetail);
        txtTrips = findViewById(R.id.txtTrips_cardetail);
        txtLocation = findViewById(R.id.txtLocation_cardetail);
        txtPrice = findViewById(R.id.txtCarPriceDetail_cardetail);
        txtDescription = findViewById(R.id.txtDescription_cardetail);
        tvCarName = findViewById(R.id.tvCarName);
        txtOwnerName = findViewById(R.id.txtOwnerName_cardetail);
        txtOwnerRating = findViewById(R.id.txtOwnerRating_cardetail);
        txtOwnerTrips = findViewById(R.id.txtOwnerTrips_cardetail);
        txtPickupLocation = findViewById(R.id.txtPickupLocation);
        txtDropoffLocation = findViewById(R.id.txtDropoffLocation);
        pickupLocationLinearLayout = findViewById(R.id.pickupLocationLinearLayout);
        dropoffLocationLinearLayout = findViewById(R.id.dropoffLocationLinearLayout);
        startTimeLinearLayout = findViewById(R.id.startTimeLinearLayout);
        endTimeLinearLayout = findViewById(R.id.endTimeLinearLayout);
        txtPickupTime = findViewById(R.id.txtPickupTime);
        txtReturnTime = findViewById(R.id.txtReturnTime);
        txtGearType = findViewById(R.id.gearType_cardetail);
        txtSeats = findViewById(R.id.seats_cardetail);
        txtFuelType = findViewById(R.id.fuelType_cardetail);
        txtEnergyConsumption = findViewById(R.id.energyConsumption_cardetail);
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

    private void setupScrollViewBehavior() {
        ScrollView scrollView = findViewById(R.id.scrollView_cardetail);
        LinearLayout layoutTopBar = findViewById(R.id.layoutTopBar_cardetail);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
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
        });
    }
    private void updateUI(CarDetailResponse carDetail) {
        Glide.with(this).load(carDetail.getImageUrl()).into(imgCar);
        txtName.setText(carDetail.getName());
        txtRating.setText(String.format("%.1f ", carDetail.getAvgRating()));
        txtTrips.setText(String.format("%d chuyến", carDetail.getTripCount()));
        txtLocation.setText(carDetail.getLocation());
        txtPrice.setText(String.format("%.0fK/ngày", carDetail.getPrice()));
        txtDescription.setText(carDetail.getDescription());
        tvCarName.setText(carDetail.getName());
        txtOwnerName.setText(carDetail.getOwnerName());
        txtOwnerRating.setText(String.format("%.1f ", carDetail.getOwnerAvgRating()));
        txtOwnerTrips.setText(String.format("%d chuyến", carDetail.getOwnerTripCount()));
        txtGearType.setText(carDetail.getGearType());
        txtSeats.setText(String.format("%d chỗ", carDetail.getSeats()));
        txtFuelType.setText(carDetail.getFuelType());
        txtEnergyConsumption.setText(String.format("%.1f L/100km", carDetail.getEnergyConsumption()));
        txtSeats = findViewById(R.id.seats_cardetail);
        txtFuelType = findViewById(R.id.fuelType_cardetail);
        txtEnergyConsumption = findViewById(R.id.energyConsumption_cardetail);
        if (selectedDate != null) {
            txtPickupLocation.setText(selectedDate.getTime().toString());
        }
    }

}
