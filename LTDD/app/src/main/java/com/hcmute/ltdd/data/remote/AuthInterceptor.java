package com.hcmute.ltdd.data.remote;

import android.content.Context;
import android.util.Log;

import com.hcmute.ltdd.utils.SharedPrefManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final Context context;

    public AuthInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SharedPrefManager.getInstance(context).getToken(); // Lấy token đã lưu

        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();

        if (token != null && !token.isEmpty()) {
            builder.addHeader("Authorization", "Bearer " + token); // Gắn token
        }

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
