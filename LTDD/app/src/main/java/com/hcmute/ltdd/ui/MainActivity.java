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
        // Dòng "Chào mừng" lên xuống
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(tvWelcome, "translationY", -10f, 10f);
        animator1.setDuration(1500);
        animator1.setRepeatMode(ObjectAnimator.REVERSE);
        animator1.setRepeatCount(ObjectAnimator.INFINITE);

        // Dòng "Bạn đến với ứng dụng thuê xe số 1 VN" - trễ 500ms
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(tvSubtitle, "translationY", -15f, 15f);
        animator2.setDuration(1500);
        animator2.setRepeatMode(ObjectAnimator.REVERSE);
        animator2.setRepeatCount(ObjectAnimator.INFINITE);
        animator2.setStartDelay(500); // Bắt đầu sau 500ms

        // Dòng "LOTOM" - trễ 1000ms (sau 1 giây)
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(tvLotom, "translationY", -12f, 12f);
        animator3.setDuration(1500);
        animator3.setRepeatMode(ObjectAnimator.REVERSE);
        animator3.setRepeatCount(ObjectAnimator.INFINITE);
        animator3.setStartDelay(1000); // Bắt đầu sau 1 giây

        // Gom tất cả vào 1 nhóm animation
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2, animator3);
        animatorSet.start();
    }

    private void startCarAnimation() {
        // Lùi lại một chút
        ObjectAnimator moveBack = ObjectAnimator.ofFloat(carImage, "translationX", -100f);
        moveBack.setDuration(800);

        // Lướt qua bên phải
        ObjectAnimator moveRight = ObjectAnimator.ofFloat(carImage, "translationX", 1000f);
        moveRight.setDuration(1200);

        // Hiệu ứng fade khói
        smokeEffect.setVisibility(View.VISIBLE);
        smokeEffect.setSpeed(0.5f);
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
