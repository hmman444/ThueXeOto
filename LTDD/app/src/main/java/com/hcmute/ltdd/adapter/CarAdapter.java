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
import com.hcmute.ltdd.model.response.CarListResponse;
import com.hcmute.ltdd.ui.CarDetailActivity;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private Context context;
    private List<CarListResponse> carList;

    public CarAdapter(Context context, List<CarListResponse> carList) {
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
        CarListResponse car = carList.get(position);

        Glide.with(context).load(car.getImageUrl()).into(holder.imgCar);

        holder.txtName.setText(car.getName());
        holder.txtSeats.setText(car.getSeats() + " chỗ");
        holder.txtGear.setText(car.getGearType().equals("Manual") ? "Số sàn" : "Tự động");
        holder.txtFuel.setText(car.getFuelType().equals("Diesel") ? "Dầu diesel" : car.getFuelType());
        holder.txtLocation.setText(car.getLocation());
        holder.txtPrice.setText(String.format("%.0fK/ngày", car.getPrice()));

        holder.txtRating.setText(String.format("%.1f ★", car.getAvgRating() != null ? car.getAvgRating() : 5.0));
        holder.txtTrips.setText(String.format("%d chuyến", car.getTripCount() != null ? car.getTripCount() : 0));


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarDetailActivity.class);
            intent.putExtra("carId", car.getCarId());
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
