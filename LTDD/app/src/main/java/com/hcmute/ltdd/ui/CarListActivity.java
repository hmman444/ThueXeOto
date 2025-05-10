package com.hcmute.ltdd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.adapter.CarAdapter;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.response.PostResponse;
import com.hcmute.ltdd.model.response.UserProfileResponse;
import com.hcmute.ltdd.ui.fragments.HomeFragment;
import com.hcmute.ltdd.utils.SharedPrefManager;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<PostResponse> postList;
    private ApiService apiService;
    private boolean isSelfDrive;

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish());

        // Nhận thông tin lựa chọn xe tự lái hay xe có tài xế từ Intent
        isSelfDrive = getIntent().getBooleanExtra("driverRequired", true); // Default là tự lái

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.rc_listCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo danh sách bài viết
        postList = new ArrayList<>();
        carAdapter = new CarAdapter(this, postList);
        recyclerView.setAdapter(carAdapter);

        // Lấy thông tin người dùng và bài viết từ API
        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();
        apiService = RetrofitClient.getRetrofit(this).create(ApiService.class);

        // Gọi API để lấy danh sách bài viết
        getPosts(token);
    }

    private void getPosts(String token) {
        apiService.getAllPosts(token).enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    postList.clear();
                    List<PostResponse> allPosts = response.body();

                    // Lọc danh sách bài viết theo driverRequired (tự lái hay có tài xế)
                    filterPostsByDriverRequired(allPosts);

                    // Cập nhật lại RecyclerView với danh sách đã lọc
                    carAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CarListActivity.this, "Không thể tải bài viết", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {
                Toast.makeText(CarListActivity.this, "Lỗi tải bài viết: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterPostsByDriverRequired(List<PostResponse> allPosts) {
        postList.clear();
        for (PostResponse post : allPosts) {
            // Lọc bài viết dựa trên lựa chọn của người dùng (tự lái hay có tài xế)
            if ((isSelfDrive && !post.isCarDriverRequired()) || (!isSelfDrive && post.isCarDriverRequired())) {
                postList.add(post);
                // Lấy thông tin chủ xe từ userId và gọi API
                getUserProfileById(post.getUserId());
            }
        }
    }

    private void getUserProfileById(Long userId) {
        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();
        apiService.getUserById(userId, token).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Cập nhật địa chỉ người đăng bài vào địa chỉ của bài viết
                    String userLocation = response.body().getAddress();
                    // Bạn có thể cần phải cập nhật giao diện với địa chỉ này
                } else {
                    Toast.makeText(CarListActivity.this, "Không thể lấy thông tin chủ xe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Toast.makeText(CarListActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
