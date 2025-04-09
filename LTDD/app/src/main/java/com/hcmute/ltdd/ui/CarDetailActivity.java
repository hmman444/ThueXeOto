package com.hcmute.ltdd.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.core.graphics.ColorUtils;

import com.hcmute.ltdd.R;

public class CarDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        ScrollView scrollView = findViewById(R.id.scrollView);
        LinearLayout layoutTopBar = findViewById(R.id.layoutTopBar);
        ImageView imgCar = findViewById(R.id.imgCar);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                int imageHeight = imgCar.getHeight();

                if (scrollY >= imageHeight) {
                    layoutTopBar.setBackgroundColor(Color.WHITE);
                } else {
                    // Tạo hiệu ứng trong suốt mượt mà
                    float alpha = (float) scrollY / (float) imageHeight;
                    int colorWithAlpha = ColorUtils.setAlphaComponent(Color.WHITE, (int) (alpha * 255));
                    layoutTopBar.setBackgroundColor(colorWithAlpha);
                }
            }
        });


    }
}
