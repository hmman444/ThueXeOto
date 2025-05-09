package com.hcmute.ltdd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.model.response.CarResponse;

import java.util.List;

public class MyCarsAdapter extends RecyclerView.Adapter<MyCarsAdapter.ViewHolder> {

    private List<CarResponse> carList;

    public MyCarsAdapter(List<CarResponse> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarResponse car = carList.get(position);
        holder.tvCarName.setText(car.getName());

        Glide.with(holder.itemView.getContext())
                .load(car.getImageUrl())
                .placeholder(R.drawable.ic_car)
                .into(holder.imgCar);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCar;
        TextView tvCarName;

        ViewHolder(View itemView) {
            super(itemView);
            imgCar = itemView.findViewById(R.id.img_car);
            tvCarName = itemView.findViewById(R.id.tv_car_name);
        }
    }
}
