package com.hcmute.ltdd.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hcmute.ltdd.R;
import com.hcmute.ltdd.adapter.ConversationAdapter;
import com.hcmute.ltdd.data.remote.ApiService;
import com.hcmute.ltdd.data.remote.RetrofitClient;
import com.hcmute.ltdd.model.response.ConversationResponse;
import com.hcmute.ltdd.model.response.UserSearchResponse;
import com.hcmute.ltdd.ui.ChatActivity;
import com.hcmute.ltdd.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private ConversationAdapter adapter;
    private ApiService apiService;

    private TextInputLayout layoutSearch;
    private TextInputEditText edtSearch;

    private List<ConversationResponse> allConversations = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = view.findViewById(R.id.recyclerConversations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        apiService = RetrofitClient.getRetrofit(requireContext()).create(ApiService.class);

        initSearch(view);
        loadConversations();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadConversations(); // Tự động gọi lại API mỗi khi fragment hiện lên
    }

    private void initSearch(View view) {
        layoutSearch = view.findViewById(R.id.layoutSearch);
        edtSearch = view.findViewById(R.id.edtSearchUser);

        layoutSearch.setEndIconOnClickListener(v -> {
            edtSearch.setText("");
            edtSearch.clearFocus();
            hideKeyboard(edtSearch);
            showConversationList(allConversations);
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().trim();
                if (keyword.isEmpty()) {
                    showConversationList(allConversations);
                } else {
                    searchUsers(keyword);
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void loadConversations() {
        String token = SharedPrefManager.getInstance(getContext()).getToken();
        apiService.getConversations("Bearer " + token)
                .enqueue(new Callback<List<ConversationResponse>>() {
                    @Override
                    public void onResponse(Call<List<ConversationResponse>> call, Response<List<ConversationResponse>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            allConversations = response.body();
                            showConversationList(allConversations);
                        } else {
                            Toast.makeText(getContext(), "Không tải được danh sách hội thoại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ConversationResponse>> call, Throwable t) {
                        Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void searchUsers(String keyword) {
        String token = "Bearer " + SharedPrefManager.getInstance(getContext()).getToken();
        apiService.searchUsers(keyword, token).enqueue(new Callback<List<UserSearchResponse>>() {
            @Override
            public void onResponse(Call<List<UserSearchResponse>> call, Response<List<UserSearchResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ConversationResponse> result = buildConversationFromSearch(response.body());
                    showConversationList(result);
                }
            }

            @Override
            public void onFailure(Call<List<UserSearchResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi tìm kiếm: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<ConversationResponse> buildConversationFromSearch(List<UserSearchResponse> users) {
        List<ConversationResponse> result = new ArrayList<>();
        for (UserSearchResponse user : users) {
            Long convId = allConversations.stream()
                    .filter(c -> c.getReceiverId().equals(user.getUserId()))
                    .map(ConversationResponse::getConversationId)
                    .findFirst()
                    .orElse(null);

            result.add(new ConversationResponse(
                    convId,
                    user.getUserId(),
                    user.getName(),
                    user.getImageUrl(),
                    "",
                    ""
            ));
        }
        return result;
    }

    private void showConversationList(List<ConversationResponse> list) {
        adapter = new ConversationAdapter(getContext(), list, conversation -> {
            Intent intent = new Intent(getContext(), ChatActivity.class);
            intent.putExtra("conversationId", conversation.getConversationId());
            intent.putExtra("receiverId", conversation.getReceiverId());
            intent.putExtra("receiverName", conversation.getReceiverName());
            intent.putExtra("receiverImage", conversation.getReceiverImage());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}