package com.hcmute.ltdd.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.response.UserProfileResponse;
import com.hcmute.ltdd.ui.AccountActivity;
import com.hcmute.ltdd.ui.AddCarActivity;
import com.hcmute.ltdd.utils.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private View accountLayout, registerRentLayout;
    private TextView tvUsername, tvPhone;
    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        accountLayout = view.findViewById(R.id.account_layout);
        registerRentLayout = view.findViewById(R.id.register_rent_layout);
        tvUsername = view.findViewById(R.id.tv_username);
        tvPhone = view.findViewById(R.id.tv_numberphone);

        apiService = RetrofitClient.getRetrofit(requireContext()).create(ApiService.class);

        loadUserProfile();

        accountLayout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AccountActivity.class));
        });

        registerRentLayout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddCarActivity.class));
        });

        return view;
    }

    private void loadUserProfile() {
        String token = "Bearer " + SharedPrefManager.getInstance(requireContext()).getToken();
        apiService.getUserProfile(token).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserProfileResponse profile = response.body();
                    tvUsername.setText(profile.getName());

                    String phone = profile.getPhone();
                    if (phone == null || phone.trim().isEmpty()) {
                        tvPhone.setText("Chưa liên kết số điện thoại");
                    } else {
                        // Đổi 0377179132 → +84 377179132
                        if (phone.startsWith("0") && phone.length() > 1) {
                            phone = "+84 " + phone.substring(1);
                        }
                        tvPhone.setText(phone);
                    }
                } else {
                    Toast.makeText(requireContext(), "Không lấy được thông tin người dùng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}