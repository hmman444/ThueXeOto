package com.hcmute.ltdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.viewmodel.AuthViewModel;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.edtUsername);
        emailEditText = findViewById(R.id.edtEmail);
        passwordEditText = findViewById(R.id.edtPassword);
        confirmPasswordEditText = findViewById(R.id.edtConfirmPassword);
        registerButton = findViewById(R.id.btnRegister);
        backButton = findViewById(R.id.btnBack);

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            Intent intent = new Intent(RegisterActivity.this, VerifyOTPActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
