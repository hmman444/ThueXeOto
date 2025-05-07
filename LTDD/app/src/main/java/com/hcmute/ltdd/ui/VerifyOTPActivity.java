package com.hcmute.ltdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.api.ApiClient;
import com.hcmute.ltdd.api.AuthApiService;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.request.VerifyOtpRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOTPActivity extends AppCompatActivity {

    private ImageButton backButton;
    private EditText otpEditText;
    private Button verifyOtpButton;
    private TextView verifyTitleTextView;

    private AuthApiService authApiService;
    private ApiService apiService;
    private String email; // Email truyền từ RegisterActivity qua

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        backButton = findViewById(R.id.btnBack);
        otpEditText = findViewById(R.id.edtOtp);
        verifyOtpButton = findViewById(R.id.btnVerifyOtp);
        verifyTitleTextView = findViewById(R.id.tvVerifyTitle);

        // Khởi tạo Retrofit Service
        authApiService = ApiClient.getClient().create(AuthApiService.class);
        apiService = RetrofitClient.getRetrofit(this).create(ApiService.class);


        // Lấy email từ Intent
        email = getIntent().getStringExtra("email");
        if (email != null) {
            verifyTitleTextView.setText("Xác minh OTP cho Email: " + email);
        }

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(VerifyOTPActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        verifyOtpButton.setOnClickListener(v -> verifyOtp());
    }

    private void verifyOtp() {
        String otpCode = otpEditText.getText().toString().trim();

        if (otpCode.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mã OTP", Toast.LENGTH_SHORT).show();
            return;
        }

        VerifyOtpRequest request = new VerifyOtpRequest(email, otpCode);

        authApiService.verifyOtp(request).enqueue(new Callback<ApiResponse<String>>() {
        apiService.verifyOtp(request).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<String> apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        Toast.makeText(VerifyOTPActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // Xác thực thành công, quay về LoginActivity
                        Intent intent = new Intent(VerifyOTPActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(VerifyOTPActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(VerifyOTPActivity.this, "Xác minh thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                Toast.makeText(VerifyOTPActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
