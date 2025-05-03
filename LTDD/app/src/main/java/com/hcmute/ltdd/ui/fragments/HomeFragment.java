package com.hcmute.ltdd.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.ui.CarListActivity;
import com.hcmute.ltdd.ui.FavoritesActivity;
import com.hcmute.ltdd.ui.HomeActivity;
import com.hcmute.ltdd.ui.LoginActivity;
import com.hcmute.ltdd.ui.RewardsActivity;

public class HomeFragment extends Fragment {

    private Button btnSelfDrive, btnWithDriver;
    private ViewFlipper viewFlipper;
    private ImageView iconHeart, iconGift;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.btnFindCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CarListActivity.class);
                startActivity(intent);
            }
        });
        // Ánh xạ các thành phần giao diện
        btnSelfDrive = view.findViewById(R.id.btnSelfDrive);
        btnWithDriver = view.findViewById(R.id.btnWithDriver);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        iconHeart = view.findViewById(R.id.iconHeart);
        iconGift = view.findViewById(R.id.iconGift);

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

        iconHeart.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FavoritesActivity.class);
            startActivity(intent);
        });

        iconGift.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RewardsActivity.class);
            startActivity(intent);
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

