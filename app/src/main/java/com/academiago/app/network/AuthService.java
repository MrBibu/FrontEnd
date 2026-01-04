package com.academiago.app.network;

import com.academiago.app.models.AuthResponse;
import com.academiago.app.models.LoginRequest;
import com.academiago.app.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    @POST("/auth/register")
    Call<AuthResponse> register(@Body RegisterRequest request);
}
