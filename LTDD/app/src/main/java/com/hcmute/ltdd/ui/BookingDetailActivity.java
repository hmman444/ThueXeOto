package com.hcmute.ltdd.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.model.response.BookingDetailResponse;
import com.hcmute.ltdd.ui.fragments.ReviewFragment;
import com.hcmute.ltdd.viewmodel.BookingViewModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingDetailActivity extends AppCompatActivity {

    private BookingViewModel bookingViewModel;

    // UI Components
    private TextView tvCarName, tvInfoName, txtPickUpTime, txtReturnTime,
            tvPickupLocation, tvDropoffLocation, tvTotalPrice, tvStatus, txtPhoneNumber,
            txtRentalPrice, txtInsuranceFee, txtDeliveryFee, txtTotalDays, txtDriverFee;
    private ImageView imgCar, imgInfo, btnClose;
    private Button btnAction, btnConfirm;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        initViews();
        setupViewModel();

        long bookingId = getIntent().getLongExtra("bookingId", -1);
        if (bookingId != -1) {
            bookingViewModel.getBookingDetail(this, bookingId);
        }
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar_bookingDetail);
        tvCarName = findViewById(R.id.tvCarName_bookingdetail);
        tvStatus = findViewById(R.id.tvStatus);
        imgCar = findViewById(R.id.imgCar_bookingdetail);
        imgInfo = findViewById(R.id.imgInfo);
        tvInfoName = findViewById(R.id.txtInfoName);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtPickUpTime = findViewById(R.id.txtPickUpTime2);
        txtReturnTime = findViewById(R.id.txtReturnTime2);
        tvPickupLocation = findViewById(R.id.txtPickupLocation2);
        tvDropoffLocation = findViewById(R.id.txtDropoffLocation2);
        txtRentalPrice = findViewById(R.id.txtRentalPrice);
        txtInsuranceFee = findViewById(R.id.txtInsuranceFee);
        txtDeliveryFee = findViewById(R.id.txtDeliveryFee);
        txtTotalDays = findViewById(R.id.txtTotalDays);
        txtDriverFee = findViewById(R.id.txtDriverFee);
        tvTotalPrice = findViewById(R.id.txtTotalPrice);
        btnAction = findViewById(R.id.btnAction);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnClose = findViewById(R.id.btnClose_bookingdetail);
    }

    private void setupViewModel() {
        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        bookingViewModel.getBookingDetailLiveData().observe(this, this::bindData);
        bookingViewModel.getIsLoading().observe(this, isLoading ->
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        bookingViewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindData(BookingDetailResponse detail) {
        setRoleAndUserInfo(detail);
        setActionButtons(detail);
        setCarInfo(detail);
        setTimeAndLocation(detail);
    }

    private void setRoleAndUserInfo(BookingDetailResponse detail) {
        String role = getIntent().getStringExtra("tab");
        boolean isRenter = "trips".equals(role);

        if (isRenter) {
            tvInfoName.setText(detail.getOwnerName());
            txtPhoneNumber.setText(detail.getOwnerPhone());
            Glide.with(this).load(detail.getOwnerImageUrl()).into(imgInfo);
        } else {
            tvInfoName.setText(detail.getRenterName());
            txtPhoneNumber.setText(detail.getRenterPhone());
            Glide.with(this).load(detail.getRenterImageUrl()).into(imgInfo);
        }
    }

    private void setActionButtons(BookingDetailResponse detail) {
        btnClose.setOnClickListener(v -> finish());
        String role = getIntent().getStringExtra("tab");
        String status = detail.getStatus();
        boolean isRenter = "trips".equals(role);
        boolean isOwner = "orders".equals(role);

        btnAction.setVisibility(View.VISIBLE);
        btnAction.setOnClickListener(null);
        btnConfirm.setVisibility(View.GONE); // Ẩn nút Confirm mặc định

        if ("Pending".equals(status)) {
            setupPendingActions(detail, isRenter, isOwner);
        } else if ("Completed".equals(status)) {
            btnAction.setVisibility(View.VISIBLE);
            btnAction.setText("Đánh giá");
            btnAction.setOnClickListener(v -> openReviewFragment(detail.getCarId()));
        } else {
            btnAction.setVisibility(View.GONE);
        }
    }

    private void setupPendingActions(BookingDetailResponse detail, boolean isRenter, boolean isOwner) {
        if (isRenter) {
            btnAction.setText("Hủy");
            btnAction.setOnClickListener(v -> showCancelDialog("Cancelled_by_user"));
        } else if (isOwner) {
            btnAction.setText("Từ chối");
            btnAction.setOnClickListener(v -> showCancelDialog("Cancelled_by_owner"));

            btnConfirm.setVisibility(View.VISIBLE);
            btnConfirm.setText("Xác nhận");
            btnConfirm.setOnClickListener(v -> bookingViewModel.updateBookingStatus(
                    this, detail.getBookingId(), "Confirmed", null
            ));
        }
    }

    private void setCarInfo(BookingDetailResponse detail) {
        tvCarName.setText(detail.getCarName());
        tvStatus.setText(detail.getStatus());
        Glide.with(this).load(detail.getCarImageUrl()).into(imgCar);
    }

    private void setTimeAndLocation(BookingDetailResponse detail) {
        txtPickUpTime.setText(formatDateTime(detail.getStartDate()));
        txtReturnTime.setText(formatDateTime(detail.getEndDate()));
        tvPickupLocation.setText(detail.getPickupLocation());
        tvDropoffLocation.setText(detail.getDropoffLocation());
    }

    private void showCancelDialog(String status) {
        new AlertDialog.Builder(this)
                .setTitle("Nhập lý do hủy")
                .setView(R.layout.dialog_cancel_reason)
                .setPositiveButton("Gửi", (dialog, which) -> {
                    EditText edtReason = ((AlertDialog) dialog).findViewById(R.id.edtCancelReason);
                    String reason = edtReason.getText().toString().trim();
                    long bookingId = getIntent().getLongExtra("bookingId", -1);
                    bookingViewModel.updateBookingStatus(this, bookingId, status, reason);
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void openReviewFragment(int carId) {
        ReviewFragment reviewFragment = new ReviewFragment(carId);
        reviewFragment.show(getSupportFragmentManager(), "ReviewFragment");
    }

    private void updateStatus(String status, String reason) {
        long bookingId = getIntent().getLongExtra("bookingId", -1);
        bookingViewModel.updateBookingStatus(this, bookingId, status, reason);
    }

    private String formatDateTime(String dateTimeStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");
        return dateTime.format(formatter);
    }
}
