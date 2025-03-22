package com.hcmute.ltdd.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.ui.AccountActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Lấy tham chiếu đến phần tử "Tài khoản của tôi"
        View accountLayout = view.findViewById(R.id.account_layout);

        // Bắt sự kiện click để mở AccountActivity
        accountLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AccountActivity.class);
            startActivity(intent);
        });

        return view;
    }
}