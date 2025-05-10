package com.hcmute.ltdd.adapter;

import android.content.Context;
import android.content.Intent;
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

        // Lấy thông tin địa chỉ của người sở hữu xe từ API (dựa trên userId trong bài viết)
        String token = "Bearer " + SharedPrefManager.getInstance(context).getToken();
        ApiService apiService = RetrofitClient.getRetrofit(context).create(ApiService.class);

        // Lấy thông tin người đăng bài (chủ xe)
        apiService.getUserById(postResponse.getUserId(), token).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String address = response.body().getAddress(); // Lấy địa chỉ từ response
                    holder.txtLocation.setText("Địa chỉ: " + address);  // Điền địa chỉ vào UI
                } else {
                    holder.txtLocation.setText("Địa chỉ không có sẵn");
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                holder.txtLocation.setText("Lỗi kết nối");
            }
        });

        // Set số lần cho thuê
        holder.txtTrips.setText(postResponse.getCarNumberOfRentals() + " chuyến");

        // Set giá thuê
        holder.txtPrice.setText(formatPrice(postResponse.getPricePerDay()));

        // Xử lý sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarDetailActivity.class);
            intent.putExtra("postId", postResponse.getPostId()); // Truyền postId qua chi tiết bài viết
            context.startActivity(intent);
        });
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
