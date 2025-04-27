package com.hcmute.ltdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hcmute.ltdd.R;

public class VerifyOTPActivity extends AppCompatActivity {
    private ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        backButton = findViewById(R.id.btnBack);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(VerifyOTPActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}