package com.hcmute.ltdd.data.repository;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.model.Feature;

import java.util.ArrayList;
import java.util.List;

public class FeatureRepository {

    private static List<Feature> featureList;

    public static List<Feature> getFeatures() {
        if (featureList == null) {
            featureList = new ArrayList<>();
            featureList.add(new Feature("Camera ô tô", false, R.drawable.ic_camera));
            featureList.add(new Feature("Lốp dự phòng", false, R.drawable.ic_spare_tire));
            featureList.add(new Feature("Bluetooth", false, R.drawable.ic_bluetooth));
            featureList.add(new Feature("Màn hình DVD", false, R.drawable.ic_dvd));
            featureList.add(new Feature("Cửa sổ trời", false, R.drawable.ic_sunroof));
            featureList.add(new Feature("ETC", false, R.drawable.ic_etc));
            featureList.add(new Feature("Túi khí an toàn", false, R.drawable.ic_air_bag));
            featureList.add(new Feature("Camera lùi", false, R.drawable.ic_camera_rear));
            featureList.add(new Feature("Bản đồ", false, R.drawable.ic_gps));
            featureList.add(new Feature("Khe cắm USB", false, R.drawable.ic_usb));
            featureList.add(new Feature("Camera cặp lề", false, R.drawable.ic_camera_side));
            featureList.add(new Feature("Camera hành trình", false, R.drawable.ic_camera_dash));
            featureList.add(new Feature("Cảm biến lốp", false, R.drawable.ic_tire_sensor));
            featureList.add(new Feature("Cảm biến va chạm", false, R.drawable.ic_collision_sensor));
            featureList.add(new Feature("Ghế trẻ em", false, R.drawable.ic_child_seat));
        }
        return featureList;
    }
}
