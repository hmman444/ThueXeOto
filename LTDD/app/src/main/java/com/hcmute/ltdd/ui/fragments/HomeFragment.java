package com.hcmute.ltdd.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.hcmute.ltdd.R;

public class HomeFragment extends Fragment {

    private Button btnSelfDrive, btnWithDriver;
    private ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Ánh xạ các thành phần giao diện
        btnSelfDrive = view.findViewById(R.id.btnSelfDrive);
        btnWithDriver = view.findViewById(R.id.btnWithDriver);
        viewFlipper = view.findViewById(R.id.viewFlipper);

        // Xử lý sự kiện khi bấm vào nút "Xe tự lái"
        btnSelfDrive.setOnClickListener(v -> {
            viewFlipper.setDisplayedChild(0); // Hiển thị tab đầu tiên
            setActiveTab(btnSelfDrive, btnWithDriver);
        });

        // Xử lý sự kiện khi bấm vào nút "Xe có tài xế"
        btnWithDriver.setOnClickListener(v -> {
            viewFlipper.setDisplayedChild(1); // Hiển thị tab thứ hai
            setActiveTab(btnWithDriver, btnSelfDrive);
        });

        // Mặc định chọn tab "Xe tự lái"
        setActiveTab(btnSelfDrive, btnWithDriver);

        return view;
    }

    // Hàm đổi trạng thái tab được chọn
    private void setActiveTab(Button active, Button inactive) {
        active.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.tab_selected));
        active.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));

        inactive.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.tab_unselected));
        inactive.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
    }
}

