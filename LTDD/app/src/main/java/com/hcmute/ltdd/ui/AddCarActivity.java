package com.hcmute.ltdd.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.adapter.FeatureAdapter;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.ApiResponse;
import com.hcmute.ltdd.model.Feature;
import com.hcmute.ltdd.model.request.AddCarRequest;
import com.hcmute.ltdd.data.repository.FeatureRepository;
import com.hcmute.ltdd.utils.CloudinaryManager;
import com.hcmute.ltdd.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCarActivity extends AppCompatActivity {

    private EditText edtCarName, edtBrand, edtEnergyConsumption, edtDescription;
    private Spinner spnGearType, spnFuelType;
    private CheckBox cbDriverRequired;
    private Button btnRegisterCar;
    private ImageView ivSelectImage, ivSelectFeatures, ivPreviewImage;
    private TextView tvSelectedFeatures;

    private ApiService apiService;
    private List<Feature> featureList;

    private Uri selectedImageUri;
    private String imageUrl;
    private ActivityResultLauncher<Intent> imagePickerLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        initViews();

        // Ẩn bàn phím khi ấn vào bất kỳ đâu trên màn hình
        findViewById(R.id.main_layout).setOnTouchListener((v, event) -> {
            hideKeyboard();
            return false;
        });

        apiService = RetrofitClient.getRetrofit(this).create(ApiService.class);
        featureList = FeatureRepository.getFeatures();

        ivSelectFeatures.setOnClickListener(v -> showSelectFeaturesDialog());
        btnRegisterCar.setOnClickListener(v -> registerCar());

        ivSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        // Khởi tạo Cloudinary
        CloudinaryManager.init(this);

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        ivPreviewImage.setImageURI(selectedImageUri);
                    } else {
                        Toast.makeText(AddCarActivity.this, "Không chọn ảnh", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void initViews() {
        edtCarName = findViewById(R.id.edtCarName);
        edtBrand = findViewById(R.id.edtBrand);
        edtEnergyConsumption = findViewById(R.id.edtEnergyConsumption);
        edtDescription = findViewById(R.id.edtDescription);
        spnGearType = findViewById(R.id.spnGearType);
        spnFuelType = findViewById(R.id.spnFuelType);
        cbDriverRequired = findViewById(R.id.cbDriverRequired);
        btnRegisterCar = findViewById(R.id.btnRegisterCar);
        ivSelectImage = findViewById(R.id.ivSelectImage);
        ivSelectFeatures = findViewById(R.id.ivSelectFeatures);
        tvSelectedFeatures = findViewById(R.id.tvSelectedFeatures);
        ivPreviewImage = findViewById(R.id.ivPreviewImage);

        // Dữ liệu cho Gear Type
        List<String> gearTypes = new ArrayList<>();
        gearTypes.add("Số sàn");
        gearTypes.add("Số tự động");

        // Dữ liệu cho Fuel Type
        List<String> fuelTypes = new ArrayList<>();
        fuelTypes.add("Điện");
        fuelTypes.add("Dầu Diesel");
        fuelTypes.add("Xăng");

        ArrayAdapter<String> gearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gearTypes);
        gearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGearType.setAdapter(gearAdapter);

        ArrayAdapter<String> fuelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fuelTypes);
        fuelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFuelType.setAdapter(fuelAdapter);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            android.view.inputmethod.InputMethodManager imm =
                    (android.view.inputmethod.InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showSelectFeaturesDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_select_features, null);

        RecyclerView rvFeatures = view.findViewById(R.id.rvFeatures);
        Button btnSelectFeatures = view.findViewById(R.id.btnSelectFeatures);

        FeatureAdapter adapter = new FeatureAdapter(this, featureList);
        rvFeatures.setLayoutManager(new LinearLayoutManager(this));
        rvFeatures.setAdapter(adapter);

        btnSelectFeatures.setOnClickListener(v1 -> {
            StringBuilder selectedFeatures = new StringBuilder();
            for (Feature feature : featureList) {
                if (feature.isSelected()) {
                    selectedFeatures.append(feature.getName()).append(", ");
                }
            }

            String selectedText = selectedFeatures.length() > 0
                    ? selectedFeatures.substring(0, selectedFeatures.length() - 2)
                    : "Chưa chọn tính năng";

            tvSelectedFeatures.setText(selectedText);
            dialog.dismiss();
        });

        dialog.setContentView(view);
        dialog.show();
    }

    private void registerCar() {
        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();

        String name = edtCarName.getText().toString().trim();
        String brand = edtBrand.getText().toString().trim();
        String description = edtDescription.getText().toString().trim();
        String gearType = spnGearType.getSelectedItem().toString();
        int seats = Integer.parseInt(((EditText) findViewById(R.id.spnSeats)).getText().toString().trim());
        String fuelType = spnFuelType.getSelectedItem().toString();
        double energyConsumption = Double.parseDouble(edtEnergyConsumption.getText().toString().trim());
        boolean driverRequired = cbDriverRequired.isChecked();

        List<String> selectedFeatures = new ArrayList<>();
        for (Feature feature : featureList) {
            if (feature.isSelected()) {
                selectedFeatures.add(feature.getName());
            }
        }

        // Kiểm tra nếu chưa chọn ảnh
        if (selectedImageUri == null) {
            Toast.makeText(this, "Vui lòng chọn ảnh trước khi đăng ký.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Nếu chưa upload ảnh, tiến hành upload
        if (imageUrl == null) {
            Toast.makeText(this, "Đang upload ảnh...", Toast.LENGTH_SHORT).show();
            CloudinaryManager.uploadImage(this, selectedImageUri, new UploadCallback() {
                @Override
                public void onStart(String requestId) {}

                @Override
                public void onProgress(String requestId, long bytes, long totalBytes) {}

                @Override
                public void onSuccess(String requestId, Map resultData) {
                    imageUrl = (String) resultData.get("secure_url");
                    proceedToRegister(name, brand, description, gearType, seats, fuelType, energyConsumption, driverRequired, selectedFeatures, imageUrl, token);
                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Toast.makeText(AddCarActivity.this, "Upload ảnh thất bại: " + error.getDescription(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {}
            });
        } else {
            // Nếu đã có `imageUrl`, tiến hành đăng ký
            proceedToRegister(name, brand, description, gearType, seats, fuelType, energyConsumption, driverRequired, selectedFeatures, imageUrl, token);
        }
    }

    private void proceedToRegister(String name, String brand, String description, String gearType, int seats,
                                   String fuelType, double energyConsumption, boolean driverRequired,
                                   List<String> features, String imageUrl, String token) {

        AddCarRequest request = new AddCarRequest(name, brand, description, gearType, seats, fuelType,
                energyConsumption, imageUrl, driverRequired, features);

        apiService.addCar(request, token).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AddCarActivity.this, "Đăng ký xe thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddCarActivity.this, "Đăng ký xe thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                Toast.makeText(AddCarActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}