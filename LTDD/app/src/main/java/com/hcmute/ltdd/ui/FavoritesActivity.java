package com.hcmute.ltdd.ui;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.ltdd.R;

public class FavoritesActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tabSelfDrive, tabWithDriver;
    private View underlineSelfDrive, underlineWithDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Ánh xạ
        btnBack = findViewById(R.id.btnBack_favorite);
        tabSelfDrive = findViewById(R.id.tabSelfDrive);
        tabWithDriver = findViewById(R.id.tabWithDriver);
        underlineSelfDrive = findViewById(R.id.underlineSelfDrive);
        underlineWithDriver = findViewById(R.id.underlineWithDriver);

        btnBack.setOnClickListener(v -> finish());

        // Tab sự kiện
        tabSelfDrive.setOnClickListener(v -> setTabSelected(true));
        tabWithDriver.setOnClickListener(v -> setTabSelected(false));

        // Mặc định tab "Xe tự lái"
        setTabSelected(true);
    }

    private void setTabSelected(boolean isSelfDrive) {
        if (isSelfDrive) {
            tabSelfDrive.setTextColor(getResources().getColor(android.R.color.black));
            tabWithDriver.setTextColor(getResources().getColor(android.R.color.darker_gray));
            underlineSelfDrive.setBackgroundColor(getResources().getColor(R.color.green_active));
            underlineWithDriver.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        } else {
            tabSelfDrive.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tabWithDriver.setTextColor(getResources().getColor(android.R.color.black));
            underlineSelfDrive.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            underlineWithDriver.setBackgroundColor(getResources().getColor(R.color.green_active));
        }
    }
}
