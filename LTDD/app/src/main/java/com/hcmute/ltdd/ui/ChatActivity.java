package com.hcmute.ltdd.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.adapter.MessageAdapter;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.ChatWebSocketClient;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.response.MessageResponse;
import com.hcmute.ltdd.model.request.MessageRequest;
import com.hcmute.ltdd.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private TextView txtReceiverName;
    private RecyclerView recyclerMessages;
    private EditText edtMessage;
    private ImageView btnSend;

    private MessageAdapter adapter;
    private List<MessageResponse> messageList;

    private long conversationId;
    private long currentUserId;
    private long receiverId;
    private String receiverName;

    private ApiService apiService;
    private ChatWebSocketClient socketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        txtReceiverName = findViewById(R.id.txtReceiverName);
        recyclerMessages = findViewById(R.id.recyclerMessages);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);

        // Nhận dữ liệu từ Intent
        conversationId = getIntent().getLongExtra("conversationId", -1);
        receiverId = getIntent().getLongExtra("receiverId", -1);
        receiverName = getIntent().getStringExtra("receiverName");
        String token = SharedPrefManager.getInstance(this).getToken();
        try {
            DecodedJWT jwt = com.auth0.jwt.JWT.decode(token);
            currentUserId = jwt.getClaim("userId").asLong();
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi khi đọc token", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        txtReceiverName.setText(receiverName);

        apiService = RetrofitClient.getRetrofit(this).create(ApiService.class);

        messageList = new ArrayList<>();
        adapter = new MessageAdapter(this, messageList, currentUserId);
        recyclerMessages.setLayoutManager(new LinearLayoutManager(this));
        recyclerMessages.setAdapter(adapter);

        loadMessages();

        socketClient = new ChatWebSocketClient(conversationId, json -> {
            runOnUiThread(() -> {
                try {
                    // Parse từ JSON và thêm vào danh sách
                    long msgId = json.getLong("messageId");
                    String content = json.getString("content");
                    long senderId = json.getLong("senderId");

                    MessageResponse newMsg = new MessageResponse(
                            msgId,
                            conversationId,
                            senderId,
                            receiverId,
                            content,
                            null,
                            "TEXT",
                            "SENDING",
                            json.getString("timestamp")
                    );

                    messageList.add(newMsg);
                    adapter.notifyItemInserted(messageList.size() - 1);
                    recyclerMessages.scrollToPosition(messageList.size() - 1);
                } catch (Exception e) {
                    Log.e("ChatActivity", "Lỗi parse tin nhắn WebSocket", e);
                }
            });
        });

        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void loadMessages() {
        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();
        apiService.getMessagesByConversation(conversationId, token).enqueue(new Callback<List<MessageResponse>>() {
            @Override
            public void onResponse(Call<List<MessageResponse>> call, Response<List<MessageResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    messageList.clear();
                    messageList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    recyclerMessages.scrollToPosition(messageList.size() - 1);
                } else {
                    Toast.makeText(ChatActivity.this, "Không tải được tin nhắn", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MessageResponse>> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Lỗi mạng: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMessage() {
        String content = edtMessage.getText().toString().trim();
        if (content.isEmpty()) return;

        String token = "Bearer " + SharedPrefManager.getInstance(this).getToken();
        MessageRequest request = new MessageRequest(receiverId, content);

        apiService.sendMessage(request, token).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    edtMessage.setText("");
                } else {
                    Toast.makeText(ChatActivity.this, "Gửi tin nhắn thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Lỗi mạng khi gửi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
