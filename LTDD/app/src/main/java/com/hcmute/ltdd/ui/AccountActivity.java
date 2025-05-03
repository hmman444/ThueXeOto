package com.hcmute.ltdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.ui.fragments.ProfileFragment;

public class AccountActivity extends AppCompatActivity {

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(AccountActivity.this, ProfileFragment.class);
            startActivity(intent);
            finish();
        });
    }
}
