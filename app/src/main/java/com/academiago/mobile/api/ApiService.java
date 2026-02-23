package com.academiago.mobile.api;

import com.academiago.mobile.model.LoginRequest;
import com.academiago.mobile.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/auth/change-password")
    <ChangePasswordRequest>
    Call<String> changePassword(@Body ChangePasswordRequest request);
}