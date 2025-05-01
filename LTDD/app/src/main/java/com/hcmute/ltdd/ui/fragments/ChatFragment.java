package com.hcmute.ltdd.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmute.ltdd.R;
import com.hcmute.ltdd.adapter.ConversationAdapter;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.response.ConversationResponse;
import com.hcmute.ltdd.ui.ChatActivity;
import com.hcmute.ltdd.utils.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private ConversationAdapter adapter;

    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.recyclerConversations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadConversations();

        return view;
    }

    private void loadConversations() {
        String token = SharedPrefManager.getInstance(getContext()).getToken();
        Log.d("ChatFragment", "Token = Bearer " + token);

        apiService = RetrofitClient.getRetrofit(requireContext()).create(ApiService.class);
        apiService.getConversations("Bearer " + token)
                .enqueue(new Callback<List<ConversationResponse>>() {
                    @Override
                    public void onResponse(Call<List<ConversationResponse>> call, Response<List<ConversationResponse>> response) {
                        Log.d("ChatFragment", "Raw response = " + response.toString());
                        Log.d("ChatFragment", "Response body = " + response.body());
                        if (response.isSuccessful() && response.body() != null) {
                            List<ConversationResponse> conversationList = response.body();
                            adapter = new ConversationAdapter(getContext(), conversationList, conversation -> {
                                Intent intent = new Intent(getContext(), ChatActivity.class);
                                intent.putExtra("conversationId", conversation.getConversationId());
                                intent.putExtra("receiverId", conversation.getReceiverId());
                                intent.putExtra("receiverName", conversation.getReceiverName());
                                intent.putExtra("receiverImage", conversation.getReceiverImage());
                                startActivity(intent);
                            });
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(getContext(), "Không tải được danh sách hội thoại", Toast.LENGTH_SHORT).show();
                            try {
                                String errorBody = response.errorBody() != null ? response.errorBody().string() : "null";
                                android.util.Log.e("ChatFragment", "Lỗi API: " + errorBody);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<List<ConversationResponse>> call, Throwable t) {
                        Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
