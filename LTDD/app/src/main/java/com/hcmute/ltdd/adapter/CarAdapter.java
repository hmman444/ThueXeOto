package com.hcmute.ltdd.adapter;

import android.content.Context;
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
        holder.txtName.setText(car.getName());
        holder.txtGear.setText(car.getGearType());
        holder.txtSeats.setText(car.getSeats() + " chỗ");
        holder.txtFuel.setText(car.getFuelType());
        holder.txtLocation.setText(car.getLocation());
        holder.txtRating.setText(String.valueOf(car.getRating()));
        holder.txtTrips.setText(car.getTrips() + " chuyến");
        holder.txtPrice.setText(car.getPrice() + "K/ngày");
        holder.txtRequests.setText(car.getRequests() + " gói 4 giờ");

        Glide.with(context).load(car.getImageUrl()).into(holder.imgCar);
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
            txtGear = itemView.findViewById(R.id.txtGear);
            txtSeats = itemView.findViewById(R.id.txtSeats);
            txtFuel = itemView.findViewById(R.id.txtFuel);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtTrips = itemView.findViewById(R.id.txtTrips);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtRequests = itemView.findViewById(R.id.txtRequests);

        }
    }
}
