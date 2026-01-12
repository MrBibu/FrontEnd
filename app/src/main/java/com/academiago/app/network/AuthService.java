package com.academiago.app.network;

import com.academiago.app.models.AuthResponse;
import com.academiago.app.models.LoginRequest;
import com.academiago.app.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthService {
    @POST("/auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    @POST("/auth/register")
    Call<AuthResponse> register(@Body RegisterRequest request);

    @POST("/auth/change-password")
    Call<String> changePassword(
            @Header("Authorization") String token,
            @Query("newPassword") String newPassword,
            @Query("logoutAllSessions") boolean logoutAllSessions );
}
