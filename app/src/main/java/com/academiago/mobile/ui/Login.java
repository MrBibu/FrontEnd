package com.academiago.mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.academiago.mobile.R;
import com.academiago.mobile.api.ApiClient;
import com.academiago.mobile.api.ApiService;
import com.academiago.mobile.model.LoginRequest;
import com.academiago.mobile.model.LoginResponse;
import com.academiago.mobile.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TokenManager.init(this);

        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    
                    TokenManager.saveToken(loginResponse.getToken());
                    TokenManager.saveRole(loginResponse.getRole());

                    // Check if user needs to change password
                    if (loginResponse.getFirstLogin() != null && loginResponse.getFirstLogin()) {
                        Intent intent = new Intent(Login.this, ChangePassword.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Login.this, DashBoard.class);
                        startActivity(intent);
                    }
                    finish();
                } else {
                    Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
