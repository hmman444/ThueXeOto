package com.hcmute.ltdd.ui;

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

    private static final String TAG = "CarListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        Log.d(TAG, "onCreate: CarListActivity started");

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish());

        // Nhận thông tin lựa chọn xe tự lái hay xe có tài xế từ Intent
        isSelfDrive = getIntent().getBooleanExtra("driverRequired", true);
        Log.d(TAG, "onCreate: isSelfDrive = " + isSelfDrive);

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.rc_listCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo danh sách bài viết
        postList = new ArrayList<>();
        carAdapter = new CarAdapter(this, postList);
        recyclerView.setAdapter(carAdapter);

        // Lấy token và khởi tạo ApiService
        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();
        Log.d(TAG, "onCreate: Token = " + token);

        apiService = RetrofitClient.getRetrofit(this).create(ApiService.class);

        // Gọi API để lấy danh sách bài viết
        getPosts(token);
    }

    private void getPosts(String token) {
        Log.d(TAG, "getPosts: Fetching posts...");
        apiService.getAllPosts(token).enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    postList.clear();
                    List<PostResponse> allPosts = response.body();

                    Log.d(TAG, "onResponse: Total posts received = " + allPosts.size());

                    // Lọc danh sách bài viết theo driverRequired (tự lái hay có tài xế)
                    filterPostsByDriverRequired(allPosts);

                    // Cập nhật lại RecyclerView
                    carAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "onResponse: Failed to load posts, response code = " + response.code());
                    Toast.makeText(CarListActivity.this, "Không thể tải bài viết", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {
                Log.e(TAG, "onFailure: Error fetching posts - " + t.getMessage());
                Toast.makeText(CarListActivity.this, "Lỗi tải bài viết: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterPostsByDriverRequired(List<PostResponse> allPosts) {
        postList.clear();
        Log.d(TAG, "filterPostsByDriverRequired: Filtering posts...");

        for (PostResponse post : allPosts) {
            Log.d(TAG, "Post ID: " + post.getPostId());
            Log.d(TAG, "Car ID: " + post.getCarId());
            Log.d(TAG, "Car Driver Required: " + post.isCarDriverRequired());
            Log.d(TAG, "Features: " + post.getFeatures());

            if ((isSelfDrive && !post.isCarDriverRequired()) || (!isSelfDrive && post.isCarDriverRequired())) {
                postList.add(post);
                // Lấy thông tin chủ xe từ userId
                getUserProfileById(post.getUserId());
            }
        }

        if (postList.isEmpty()) {
            Log.d(TAG, "filterPostsByDriverRequired: No posts found matching criteria");
        } else {
            Log.d(TAG, "filterPostsByDriverRequired: Posts found = " + postList.size());
        }
    }

    private void getUserProfileById(Long userId) {
        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();
        Log.d(TAG, "getUserProfileById: Fetching user profile for userId = " + userId);

        apiService.getUserById(userId, token).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "getUserProfileById: User profile fetched successfully");
                } else {
                    Log.e(TAG, "getUserProfileById: Failed to load user profile, response code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Log.e(TAG, "getUserProfileById: Error fetching user profile - " + t.getMessage());
            }
        });
    }
}
