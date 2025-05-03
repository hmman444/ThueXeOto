package com.hcmute.ltdd.ui;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.ltdd.R;

public class RewardsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tabActive, tabExpired;
    private View underlineActive, underlineExpired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        // Ánh xạ
        btnBack = findViewById(R.id.btnBack);
        tabActive = findViewById(R.id.tabActive);
        tabExpired = findViewById(R.id.tabExpired);
        underlineActive = findViewById(R.id.underlineActive);
        underlineExpired = findViewById(R.id.underlineExpired);

        // Quay lại
        btnBack.setOnClickListener(v -> finish());

        // Tab 1: Hiện hữu
        tabActive.setOnClickListener(v -> setTabSelected(true));

        // Tab 2: Hết hiệu lực
        tabExpired.setOnClickListener(v -> setTabSelected(false));
    }

    private void setTabSelected(boolean isActiveSelected) {
        if (isActiveSelected) {
            tabActive.setTextColor(getResources().getColor(android.R.color.black));
            tabExpired.setTextColor(getResources().getColor(android.R.color.darker_gray));
            underlineActive.setBackgroundColor(getResources().getColor(R.color.green_active));
            underlineExpired.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        } else {
            tabActive.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tabExpired.setTextColor(getResources().getColor(android.R.color.black));
            underlineActive.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            underlineExpired.setBackgroundColor(getResources().getColor(R.color.green_active));
        }
    }
}
