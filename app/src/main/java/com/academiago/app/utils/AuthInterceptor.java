package com.academiago.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

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
        SharedPreferences prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String jwt = prefs.getString("jwt", null);

        Request original = chain.request();
        if (jwt != null) {
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer " + jwt)
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(original);
    }
}
