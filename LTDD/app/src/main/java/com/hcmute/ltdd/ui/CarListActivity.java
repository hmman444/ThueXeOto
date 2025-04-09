package com.hcmute.ltdd.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.adapter.CarAdapter;
import com.hcmute.ltdd.model.Car;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        // Thiết lập AutoCompleteTextView cho danh sách quận
        AutoCompleteTextView actvDistrict = findViewById(R.id.actv_district);
        String[] districts = {"Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận 9",
                "Quận 10", "Quận 11", "Quận 12", "Bình Thạnh", "Gò Vấp", "Phú Nhuận", "Tân Bình", "Tân Phú", "Thủ Đức"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, districts);
        actvDistrict.setAdapter(adapter);
        actvDistrict.setOnClickListener(v -> actvDistrict.showDropDown());

        // Khởi tạo RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rc_listCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách xe
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(
                1,  // carId
                101, // ownerId
                "FORD RANGER XLS 4x2 2022",
                "Xe bán tải mạnh mẽ, tiết kiệm nhiên liệu.",
                "Manual",
                4,
                "Diesel",
                7,
                new String[]{"Điều hòa", "Bluetooth", "Camera lùi"},
                "Huyện Đức Hòa, Long An",
                1090.0,
                "https://fordlongbien.com/wp-content/uploads/2022/08/ford-ranger-wildtrak-mau-do-cam-icon-fordlongbien.jpg",
                "available",
                false,
                new Date()
        ));

        carList.add(new Car(
                2,
                102,
                "TOYOTA VIOS 2023",
                "Dòng sedan phổ thông, tiết kiệm xăng.",
                "Automatic",
                5,
                "Gasoline",
                6,
                new String[]{"Điều hòa", "Cửa sổ trời", "Cảm biến lùi"},
                "Quận Bình Thạnh, TP.HCM",
                850.0,
                "https://fordlongbien.com/wp-content/uploads/2022/08/ford-ranger-wildtrak-mau-do-cam-icon-fordlongbien.jpg",
                "booked",
                true,
                new Date()
        ));

        // Gán Adapter
        CarAdapter carAdapter = new CarAdapter(this, carList);
        recyclerView.setAdapter(carAdapter);
    }
}
