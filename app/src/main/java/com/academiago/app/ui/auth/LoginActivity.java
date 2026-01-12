package com.academiago.app.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.R;
import com.academiago.app.models.LoginRequest;
import com.academiago.app.models.AuthResponse;
import com.academiago.app.network.ApiClient;
import com.academiago.app.network.AuthService;
import com.academiago.app.ui.student.StudentDashboardActivity;
import com.academiago.app.ui.teacher.TeacherDashboardActivity;
import com.academiago.app.ui.admin.AdminDashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailInput, passwordInput;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password required", Toast.LENGTH_SHORT).show();
                return;
            }

            AuthService service = ApiClient.getClient().create(AuthService.class);
            LoginRequest req = new LoginRequest(email, password);

            service.login(req).enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        AuthResponse auth = response.body();

                        // Save JWT, email, role
                        SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
                        prefs.edit()
                                .putString("jwt", auth.getToken())
                                .putString("email", auth.getEmail())
                                .putString("role", auth.getRole())
                                .apply();

                        // Route based on role
                        Intent intent;
                        switch (auth.getRole().toLowerCase()) {
                            case "student":
                                intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                                break;
                            case "teacher":
                                intent = new Intent(LoginActivity.this, TeacherDashboardActivity.class);
                                break;
                            case "admin":
                                intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                                break;
                            default:
                                Toast.makeText(LoginActivity.this, "Unknown role", Toast.LENGTH_SHORT).show();
                                return;
                        }

                        startActivity(intent);
                        finish();
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
    }
}
