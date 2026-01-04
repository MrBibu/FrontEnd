package com.academiago.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.R;
import com.academiago.app.models.LoginRequest;
import com.academiago.app.models.AuthResponse;
import com.academiago.app.network.ApiClient;
import com.academiago.app.network.AuthService;
import com.academiago.app.utils.TokenUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailInput, passwordInput;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // connect to XML layout

        // Bind UI elements
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        // Handle login button click
        loginBtn.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            AuthService service = ApiClient.getClient().create(AuthService.class);
            service.login(new LoginRequest(email, password)).enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String token = response.body().getAccessToken();

                        // Decode role from JWT
                        String role = TokenUtils.getRole(token);

                        // Save token securely (basic SharedPreferences)
                        getSharedPreferences("auth", MODE_PRIVATE)
                                .edit()
                                .putString("jwt", token)
                                .apply();

                        // Navigate by role
                        if ("STUDENT".equalsIgnoreCase(role)) {
                            startActivity(new Intent(LoginActivity.this, StudentDashboardActivity.class));
                        } else if ("TEACHER".equalsIgnoreCase(role)) {
                            startActivity(new Intent(LoginActivity.this, TeacherDashboardActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Role missing/unknown", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Register link navigation
        TextView registerLink = findViewById(R.id.registerLink);
        registerLink.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
