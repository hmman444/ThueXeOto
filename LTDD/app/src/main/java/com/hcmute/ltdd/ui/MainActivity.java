package com.hcmute.ltdd.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.hcmute.ltdd.R;

public class MainActivity extends AppCompatActivity {
    private TextView tvWelcome, tvSubtitle, tvLotom;
    private ImageView carImage;
    private LottieAnimationView smokeEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        tvWelcome = findViewById(R.id.tvWelcome);
        tvSubtitle = findViewById(R.id.tvSubtitle);
        tvLotom = findViewById(R.id.tvLotom);
        carImage = findViewById(R.id.carImage);
        smokeEffect = findViewById(R.id.smokeEffect);

        // Gọi animation cho text
        startTextAnimation();

        // Xử lý sự kiện khi nhấn vào ô tô
        carImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCarAnimation();
            }
        });
    }

    private void startTextAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(tvWelcome, "translationY", -10f, 10f);
        animator1.setDuration(1500);
        animator1.setRepeatMode(ObjectAnimator.REVERSE);
        animator1.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(tvSubtitle, "translationY", -15f, 15f);
        animator2.setDuration(1800);
        animator2.setRepeatMode(ObjectAnimator.REVERSE);
        animator2.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(tvLotom, "scaleX", 1f, 1.1f);
        animator3.setDuration(1000);
        animator3.setRepeatMode(ObjectAnimator.REVERSE);
        animator3.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator animator4 = ObjectAnimator.ofFloat(tvLotom, "scaleY", 1f, 1.1f);
        animator4.setDuration(1000);
        animator4.setRepeatMode(ObjectAnimator.REVERSE);
        animator4.setRepeatCount(ObjectAnimator.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2, animator3, animator4);
        animatorSet.start();
    }

    private void startCarAnimation() {
        // Lùi lại một chút
        ObjectAnimator moveBack = ObjectAnimator.ofFloat(carImage, "translationX", -30f);
        moveBack.setDuration(200);

        // Lướt qua bên phải
        ObjectAnimator moveRight = ObjectAnimator.ofFloat(carImage, "translationX", 1000f);
        moveRight.setDuration(800);

        // Hiệu ứng fade khói
        smokeEffect.setVisibility(View.VISIBLE);
        smokeEffect.playAnimation();

        // Chạy animation
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(moveBack, moveRight);
        animatorSet.start();

        // Chuyển màn hình sau khi animation kết thúc
        moveRight.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override public void onAnimationStart(Animator animation) {}
            @Override public void onAnimationCancel(Animator animation) {}
            @Override public void onAnimationRepeat(Animator animation) {}
        });
    }
}
