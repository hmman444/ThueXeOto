package com.hcmute.ltdd.adapter;

import android.content.Context;
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
import com.hcmute.ltdd.model.response.ConversationResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {

    private Context context;
    private List<ConversationResponse> conversationList;
    private OnConversationClickListener listener;

    public interface OnConversationClickListener {
        void onConversationClick(ConversationResponse conversation);
    }

    public ConversationAdapter(Context context, List<ConversationResponse> conversationList, OnConversationClickListener listener) {
        this.context = context;
        this.conversationList = conversationList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_conversation, parent, false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        ConversationResponse item = conversationList.get(position);

        holder.txtReceiverName.setText(item.getReceiverName());
        holder.txtLastMessage.setText(item.getLastMessageContent());

        Log.d("💬ConversationAdapter", "⏳ Raw timestamp = " + item.getLastMessageTimestamp());

        String timeDisplay = formatRelativeTime(item.getLastMessageTimestamp());
        holder.txtLastTime.setText(timeDisplay);

        Glide.with(context)
                .load(item.getReceiverImage())
                .placeholder(R.drawable.avatar)
                .into(holder.imgReceiverAvatar);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onConversationClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return conversationList != null ? conversationList.size() : 0;
    }

    public static class ConversationViewHolder extends RecyclerView.ViewHolder {
        ImageView imgReceiverAvatar;
        TextView txtReceiverName, txtLastMessage, txtLastTime;

        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);
            imgReceiverAvatar = itemView.findViewById(R.id.imgReceiverAvatar);
            txtReceiverName = itemView.findViewById(R.id.txtReceiverName);
            txtLastMessage = itemView.findViewById(R.id.txtLastMessage);
            txtLastTime = itemView.findViewById(R.id.txtLastTime);
        }
    }

    private String formatRelativeTime(String timestamp) {
        try {
            Log.d("💬ConversationAdapter", "⏱ Parsing timestamp: " + timestamp);

            // Không ép timezone nếu server trả giờ theo giờ VN mà không có ký hiệu múi giờ
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

            Date messageTime = sdf.parse(timestamp);
            if (messageTime == null) return "";

            long now = System.currentTimeMillis();
            long msgTime = messageTime.getTime();

            Log.d("💬ConversationAdapter", "📆 Now(ms): " + now + " | Msg(ms): " + msgTime);

            long diffMillis = now - msgTime;
            long seconds = diffMillis / 1000;
            long minutes = seconds / 60;
            long hours   = minutes / 60;
            long days    = hours / 24;

            Log.d("💬ConversationAdapter", "📊 DIFF → " + seconds + "s | " + minutes + "m | " + hours + "h | " + days + "d");

            String result;
            if (seconds < 60) {
                result = "Vừa xong";
            } else if (minutes < 60) {
                result = minutes + " phút trước";
            } else if (hours < 24) {
                result = hours + " giờ trước";
            } else if (days == 1) {
                result = "Hôm qua";
            } else if (days < 7) {
                result = days + " ngày trước";
            } else {
                result = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(messageTime);
            }

            Log.d("💬ConversationAdapter", "✅ RESULT TIME: " + result);
            return result;

        } catch (Exception e) {
            Log.e("💬ConversationAdapter", "❌ Error parsing time: " + e.getMessage());
            return "";
        }
    }
}
