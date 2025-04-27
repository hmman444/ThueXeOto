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
import com.hcmute.ltdd.model.Car;
import com.hcmute.ltdd.ui.CarDetailActivity;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private Context context;
    private List<Car> carList;

    public CarAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);

        // Load ảnh xe từ URL
        Glide.with(context).load(car.getImageUrl()).into(holder.imgCar);

        // Set thông tin xe
        holder.txtName.setText(car.getName());
        holder.txtSeats.setText(car.getSeats() + " chỗ");
        holder.txtGear.setText(car.getGearType().equals("Manual") ? "Số sàn" : "Tự động");
        holder.txtFuel.setText(car.getFuelType().equals("Diesel") ? "Dầu diesel" : car.getFuelType());
        holder.txtLocation.setText(car.getLocation());
        holder.txtPrice.setText(String.format("%.2fK/ngày", car.getPrice()));

        // Xếp hạng (giả lập, cần có dữ liệu từ model)
        holder.txtRating.setText("5.0 ★");
        holder.txtTrips.setText("1 chuyến");

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarDetailActivity.class);
            intent.putExtra("carId", car.getCarId()); // Pass car ID to detail activity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCar;
        TextView txtName, txtGear, txtSeats, txtFuel, txtLocation, txtRating, txtTrips, txtPrice, txtRequests;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCar = itemView.findViewById(R.id.imgCar);
            txtName = itemView.findViewById(R.id.txtName);
            txtSeats = itemView.findViewById(R.id.txtSeats);
            txtGear = itemView.findViewById(R.id.txtGear);
            txtFuel = itemView.findViewById(R.id.txtFuel);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtTrips = itemView.findViewById(R.id.txtTrips);
        }
    }
}
