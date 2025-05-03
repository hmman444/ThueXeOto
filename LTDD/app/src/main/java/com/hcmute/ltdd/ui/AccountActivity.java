package com.hcmute.ltdd.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
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

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Nút quay lại
        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish());

        // Thông tin tên và ngày tạo
        tvUsername = findViewById(R.id.tv_username);
        tvJoinDate = findViewById(R.id.tv_join_date);

        // Các icon + text trạng thái theo ID đã sửa trong XML
        iconBirth = findViewById(R.id.iconBirth);
        statusBirth = findViewById(R.id.statusBirth);

        iconGender = findViewById(R.id.icon_Gender);
        statusGender = findViewById(R.id.status_Gender);

        iconPhone = findViewById(R.id.iconPhone);
        statusPhone = findViewById(R.id.statusPhone);

        // Gọi API
        apiService = RetrofitClient.getRetrofit(this).create(ApiService.class);
        loadUserProfile();
    }

    private void loadUserProfile() {
        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();
        apiService.getUserProfile(token).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserProfileResponse user = response.body();

                    // Tên và ngày tham gia
                    tvUsername.setText(user.getName());
                    tvJoinDate.setText("Ngày tham gia: " + formatDate(user.getCreatedAt()));


                    // Các trường thông tin khác
                    updateVerification(iconBirth, statusBirth, user.getBirthdate());
                    updateVerification(iconGender, statusGender, user.getGender());
                    updateVerification(iconPhone, statusPhone, user.getPhone());

                } else {
                    Toast.makeText(AccountActivity.this, "Không tải được thông tin", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
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
            // ISO 8601 -> Date
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
            Date date = isoFormat.parse(isoDateTime);

            // Date -> dd/MM/yyyy
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Không rõ";
        }
    }

}
