package com.hcmute.ltdd.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.response.UserProfileResponse;
import com.hcmute.ltdd.utils.SharedPrefManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView tvUsername, tvJoinDate;

    private ImageView iconBirth, iconGender, iconPhone;
    private TextView statusBirth, statusGender, statusPhone;

    private String valueBirth, valueGender, valuePhone, valueEmail;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish());

        tvUsername = findViewById(R.id.tv_username);
        tvJoinDate = findViewById(R.id.tv_join_date);

        iconBirth = findViewById(R.id.iconBirth);
        statusBirth = findViewById(R.id.statusBirth);

        iconGender = findViewById(R.id.icon_Gender);
        statusGender = findViewById(R.id.status_Gender);

        iconPhone = findViewById(R.id.iconPhone);
        statusPhone = findViewById(R.id.statusPhone);

        apiService = RetrofitClient.getRetrofit(this).create(ApiService.class);
        loadUserProfile();

        findViewById(R.id.account_Birth).setOnClickListener(v -> showDetailDialog(valueBirth, "Chưa liên kết ngày sinh"));
        findViewById(R.id.account_Gender).setOnClickListener(v -> showDetailDialog(valueGender, "Chưa liên kết giới tính"));
        findViewById(R.id.account_Phone).setOnClickListener(v -> showDetailDialog(valuePhone, "Chưa liên kết số điện thoại"));
        findViewById(R.id.account_Email).setOnClickListener(v -> showDetailDialog(valueEmail, "Email tạm thời bị lỗi"));
    }

    private void loadUserProfile() {
        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();
        apiService.getUserProfile(token).enqueue(new Callback<ApiResponse<UserProfileResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserProfileResponse>> call, Response<ApiResponse<UserProfileResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<UserProfileResponse> apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        UserProfileResponse user = apiResponse.getData();

                        tvUsername.setText(user.getName());
                        tvJoinDate.setText("Ngày tham gia: " + formatDate(user.getCreatedAt()));

                        valueBirth = user.getBirthdate();
                        valueGender = user.getGender();
                        valuePhone = user.getPhone();
                        valueEmail = user.getEmail();

                        updateVerification(iconBirth, statusBirth, valueBirth);
                        updateVerification(iconGender, statusGender, valueGender);
                        updateVerification(iconPhone, statusPhone, valuePhone);

                    } else {
                        Toast.makeText(AccountActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AccountActivity.this, "Không tải được thông tin", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserProfileResponse>> call, Throwable t) {
                Toast.makeText(AccountActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateVerification(ImageView iconView, TextView statusView, String fieldValue) {
        if (fieldValue != null && !fieldValue.trim().isEmpty()) {
            iconView.setImageResource(R.drawable.ic_verify);
            statusView.setText("Đã xác thực");
            statusView.setTextColor(getResources().getColor(R.color.green_700));
        } else {
            iconView.setImageResource(R.drawable.ic_warning);
            statusView.setText("Chưa xác thực");
            statusView.setTextColor(getResources().getColor(R.color.bright_orange));
        }
    }

    private String formatDate(String isoDateTime) {
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
            Date date = isoFormat.parse(isoDateTime);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Không rõ";
        }
    }

    private void showDetailDialog(String value, String fallback) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_detail_info, null);
        TextView tvInfo = view.findViewById(R.id.tv_info);
        tvInfo.setText((value != null && !value.isEmpty()) ? value : fallback);
        dialog.setContentView(view);
        dialog.show();
    }
}
