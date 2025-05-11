package com.hcmute.ltdd.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.response.PostResponse;
import com.hcmute.ltdd.model.response.UserProfileResponse;
import com.hcmute.ltdd.ui.CarDetailActivity;
import com.hcmute.ltdd.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private Context context;
    private List<PostResponse> postList;

    public CarAdapter(Context context, List<PostResponse> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        PostResponse postResponse = postList.get(position);

        Log.d("CarAdapter", "onBindViewHolder - Position: " + position);
        Log.d("CarAdapter", "Post ID: " + postResponse.getPostId());
        Log.d("CarAdapter", "Car ID: " + postResponse.getCarId());
        Log.d("CarAdapter", "Car Name: " + postResponse.getCarName());
        Log.d("CarAdapter", "Features: " + postResponse.getFeatures());
        Log.d("CarAdapter", "User ID: " + postResponse.getUserId());

        // Set tên xe
        holder.txtName.setText(postResponse.getCarName());

        // Set thông tin số ghế và hộp số
        holder.txtSeats.setText(postResponse.getCarSeats() + " chỗ");
        holder.txtGear.setText(postResponse.getCarGearType());

        // Set loại nhiên liệu
        holder.txtFuel.setText(postResponse.getCarFuelType());

        // Set ảnh xe
        Glide.with(context)
                .load(postResponse.getCarImageUrl())
                .into(holder.imgCar);

        Log.d("CarAdapter", "Car Image URL: " + postResponse.getCarImageUrl());

        // Lấy thông tin địa chỉ của người sở hữu xe từ API (dựa trên userId trong bài viết)
        String token = "Bearer " + SharedPrefManager.getInstance(context).getToken();
        ApiService apiService = RetrofitClient.getRetrofit(context).create(ApiService.class);

        // Lấy thông tin người đăng bài (chủ xe)
        apiService.getUserById(postResponse.getUserId(), token).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String address = response.body().getAddress();
                    holder.txtLocation.setText("Địa chỉ: " + address);

                    Log.d("CarAdapter", "User Address: " + address);

                    holder.itemView.setOnClickListener(v -> {
                        Log.d("CarAdapter", "Item Clicked - Post ID: " + postResponse.getPostId());

                        Intent intent = new Intent(context, CarDetailActivity.class);
                        intent.putExtra("carName", postResponse.getCarName());
                        intent.putExtra("carImageUrl", postResponse.getCarImageUrl());
                        intent.putExtra("carNumberOfRentals", postResponse.getCarNumberOfRentals());
                        intent.putExtra("carLocation", address);
                        intent.putExtra("carDescription", postResponse.getCarDescription());
                        intent.putExtra("postDescription", postResponse.getDescription());
                        intent.putExtra("carGearType", postResponse.getCarGearType());
                        intent.putExtra("carSeats", postResponse.getCarSeats());
                        intent.putExtra("carFuelType", postResponse.getCarFuelType());
                        intent.putExtra("carEnergyConsumption", postResponse.getCarEnergyConsumption());
                        intent.putStringArrayListExtra("carFeatures", new ArrayList<>(postResponse.getFeatures()));

                        Log.d("CarAdapter", "Intent Data - Car Name: " + postResponse.getCarName());
                        Log.d("CarAdapter", "Intent Data - Car Features: " + postResponse.getFeatures());
                        Log.d("CarAdapter", "Intent Data - Car Image URL: " + postResponse.getCarImageUrl());
                        Log.d("CarAdapter", "Intent Data - Number of Rentals: " + postResponse.getCarNumberOfRentals());
                        Log.d("CarAdapter", "Intent Data - Price Per Day: " + postResponse.getPricePerDay());

                        Log.d("CarAdapter", "Intent Data - Features: " + new ArrayList<>(postResponse.getFeatures()));

                        context.startActivity(intent);
                    });

                } else {
                    Log.w("CarAdapter", "Failed to load user address. Response Code: " + response.code());
                    holder.txtLocation.setText("Địa chỉ không có sẵn");
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Log.e("CarAdapter", "Failed to load user address: " + t.getMessage());
                holder.txtLocation.setText("Lỗi kết nối");
            }
        });

        // Set số lần cho thuê
        holder.txtTrips.setText(postResponse.getCarNumberOfRentals() + " chuyến");
        Log.d("CarAdapter", "Number of Rentals: " + postResponse.getCarNumberOfRentals());

        // Set giá thuê
        holder.txtPrice.setText(formatPrice(postResponse.getPricePerDay()));
        Log.d("CarAdapter", "Price Per Day: " + postResponse.getPricePerDay());
    }

    private String formatPrice(double price) {
        return String.format("%,.0f VNĐ/ngày", price);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCar;
        TextView txtName, txtGear, txtSeats, txtFuel, txtLocation, txtTrips, txtPrice;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCar = itemView.findViewById(R.id.imgCar);
            txtName = itemView.findViewById(R.id.txtName);
            txtSeats = itemView.findViewById(R.id.txtSeats);
            txtGear = itemView.findViewById(R.id.txtGear);
            txtFuel = itemView.findViewById(R.id.txtFuel);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtTrips = itemView.findViewById(R.id.txtTrips);
        }
    }
}
