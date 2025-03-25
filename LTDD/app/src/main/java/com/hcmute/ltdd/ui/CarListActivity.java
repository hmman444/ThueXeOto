package com.hcmute.ltdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.adapter.CarAdapter;
import com.hcmute.ltdd.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        AutoCompleteTextView actvDistrict = findViewById(R.id.actv_district);

        String[] districts = {"Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận 9",
                "Quận 10", "Quận 11", "Quận 12", "Bình Thạnh", "Gò Vấp", "Phú Nhuận", "Tân Bình", "Tân Phú", "Thủ Đức"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, districts);
        actvDistrict.setAdapter(adapter);

        actvDistrict.setOnClickListener(v -> actvDistrict.showDropDown());

        RecyclerView recyclerView = findViewById(R.id.rc_listCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("https://fordlongbien.com/wp-content/uploads/2022/08/ford-ranger-wildtrak-mau-do-cam-icon-fordlongbien.jpg", "FORD RANGER XLS 4x2 2022", "Số sàn", 4, "Dầu diesel", "Huyện Đức Hòa, Long An", 5.0, 1, "1.090", 654));

        CarAdapter carAdapter = new CarAdapter(this, carList);
        recyclerView.setAdapter(carAdapter);

    }
}