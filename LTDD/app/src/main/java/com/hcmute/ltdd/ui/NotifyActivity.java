package com.hcmute.ltdd.ui;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.ltdd.R;

public class NotifyActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tabUnread, tabRead;
    private View underlineUnread, underlineRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        // Ánh xạ
        btnBack = findViewById(R.id.btnBack);
        tabUnread = findViewById(R.id.tabUnread);
        tabRead = findViewById(R.id.tabRead);
        underlineUnread = findViewById(R.id.underlineUnread);
        underlineRead = findViewById(R.id.underlineRead);

        // Quay lại
        btnBack.setOnClickListener(v -> finish());

        // Tab 1: Chưa đọc
        tabUnread.setOnClickListener(v -> setTabSelected(true));

        // Tab 2: Đã đọc
        tabRead.setOnClickListener(v -> setTabSelected(false));
    }

    private void setTabSelected(boolean isUnreadSelected) {
        if (isUnreadSelected) {
            tabUnread.setTextColor(getResources().getColor(android.R.color.black));
            tabRead.setTextColor(getResources().getColor(android.R.color.darker_gray));
            underlineUnread.setBackgroundColor(getResources().getColor(R.color.green_active));
            underlineRead.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        } else {
            tabUnread.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tabRead.setTextColor(getResources().getColor(android.R.color.black));
            underlineUnread.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            underlineRead.setBackgroundColor(getResources().getColor(R.color.green_active));
        }
    }
}
