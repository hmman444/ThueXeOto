package com.hcmute.ltdd.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:9099")
                    .addConverterFactory(GsonConverterFactory.create())  // Chuyển đổi JSON thành đối tượng Java
                    .build();
        }
        return retrofit;
    }
}
