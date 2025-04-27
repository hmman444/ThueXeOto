package com.hcmute.ltdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.api.ApiClient;
import com.hcmute.ltdd.api.AuthApiService;
import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.request.ForgotPasswordRequest;
import com.hcmute.ltdd.model.request.ResetPasswordRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageButton backButton, sendOtpButton;
    private EditText emailEditText, otpEditText, newPasswordEditText;
    private Button resetPasswordButton;
    private TextView otpCountdownTextView;

    private AuthApiService authApiService;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        backButton = findViewById(R.id.btnBack);
        sendOtpButton = findViewById(R.id.btnSendOtp);
        emailEditText = findViewById(R.id.edtEmail);
        otpEditText = findViewById(R.id.edtOtp);
        newPasswordEditText = findViewById(R.id.edtNewPassword);
        resetPasswordButton = findViewById(R.id.btnResetPassword);
        otpCountdownTextView = findViewById(R.id.tvOtpCountdown);

        authApiService = ApiClient.getClient().create(AuthApiService.class);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        sendOtpButton.setOnClickListener(v -> sendOtp());

        resetPasswordButton.setOnClickListener(v -> resetPassword());
    }

    private void sendOtp() {
        String email = emailEditText.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }

        ForgotPasswordRequest request = new ForgotPasswordRequest(email);

        authApiService.forgotPassword(request).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<String> apiResponse = response.body();
                    Toast.makeText(ForgotPasswordActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    if (apiResponse.isSuccess()) {
                        startOtpCountdown();
                    }
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Gửi OTP thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startOtpCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        otpCountdownTextView.setVisibility(TextView.VISIBLE);

        countDownTimer = new CountDownTimer(5 * 60 * 1000, 1000) { // 5 phút
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 60000;
                long seconds = (millisUntilFinished % 60000) / 1000;
                otpCountdownTextView.setText(String.format("Mã OTP đã được gửi, OTP tồn tại trong %d:%02d phút", minutes, seconds));
            }

            @Override
            public void onFinish() {
                otpCountdownTextView.setText("Mã OTP đã hết hạn");
            }
        }.start();
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();
        String otpCode = otpEditText.getText().toString().trim();
        String newPassword = newPasswordEditText.getText().toString().trim();

        if (email.isEmpty() || otpCode.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        ResetPasswordRequest request = new ResetPasswordRequest(email, otpCode, newPassword);

        authApiService.resetPassword(request).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<String> apiResponse = response.body();
                    Toast.makeText(ForgotPasswordActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    if (apiResponse.isSuccess()) {
                        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Đổi mật khẩu thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
