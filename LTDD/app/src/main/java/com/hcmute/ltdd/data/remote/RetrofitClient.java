package com.hcmute.ltdd.data.remote;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    //private static final String BASE_URL = "http://192.168.182.130:9099/";
    private static final String BASE_URL = "http://192.168.1.2:9099/";
    public static Retrofit getRetrofit(Context context) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(context))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())  // Chuyển đổi JSON thành đối tượng Java
                    .build();
        }
        return retrofit;
    }
}
