package com.hcmute.ltdd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.model.Feature;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {

    private final Context context;
    private final List<Feature> featureList;

    public FeatureAdapter(Context context, List<Feature> featureList) {
        this.context = context;
        this.featureList = featureList;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feature, parent, false);
        return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        Feature feature = featureList.get(position);
        holder.tvFeatureName.setText(feature.getName());
        holder.iconFeature.setImageResource(feature.getIconResId());
        holder.checkboxFeature.setChecked(feature.isSelected());

        holder.checkboxFeature.setOnCheckedChangeListener((buttonView, isChecked) -> {
            feature.setSelected(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return featureList.size();
    }

    public static class FeatureViewHolder extends RecyclerView.ViewHolder {
        ImageView iconFeature;
        TextView tvFeatureName;
        CheckBox checkboxFeature;

        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            iconFeature = itemView.findViewById(R.id.iconFeature);
            tvFeatureName = itemView.findViewById(R.id.tvFeatureName);
            checkboxFeature = itemView.findViewById(R.id.checkboxFeature);
        }
    }
}
