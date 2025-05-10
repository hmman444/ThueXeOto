package com.hcmute.ltdd.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hcmute.ltdd.R;

import java.util.ArrayList;
import java.util.List;

public class LocationBottomSheet extends BottomSheetDialogFragment {

    public interface LocationListener {
        void onLocationSelected(String location);
    }

    private LocationListener listener;

    public void setLocationListener(LocationListener listener) {
        this.listener = listener;
    }

    private Spinner spinnerDistrict;
    private EditText edtDetailedAddress;
    private Button btnConfirmLocation;

    private final String[] districts = {
            "Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5",
            "Quận 6", "Quận 7", "Quận 8", "Quận 9", "Quận 10",
            "Quận 11", "Quận 12", "Bình Thạnh", "Gò Vấp",
            "Phú Nhuận", "Tân Bình", "Tân Phú", "Thủ Đức",
            "Bình Tân", "Cần Giờ", "Củ Chi", "Hóc Môn", "Nhà Bè"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_bottom_sheet, container, false);

        spinnerDistrict = view.findViewById(R.id.spinnerDistrict);
        edtDetailedAddress = view.findViewById(R.id.edtDetailedAddress);
        btnConfirmLocation = view.findViewById(R.id.btnConfirmLocation);

        setupSpinner();
        setupButton();

        return view;
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, districts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(adapter);
    }

    private void setupButton() {
        btnConfirmLocation.setOnClickListener(v -> {
            String selectedDistrict = spinnerDistrict.getSelectedItem().toString();
            String detailedAddress = edtDetailedAddress.getText().toString().trim();

            if (detailedAddress.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập địa chỉ chi tiết!", Toast.LENGTH_SHORT).show();
                return;
            }

            String fullAddress = selectedDistrict + ", " + detailedAddress;

            if (listener != null) {
                listener.onLocationSelected(fullAddress);
            }
            dismiss();
        });
    }
}
