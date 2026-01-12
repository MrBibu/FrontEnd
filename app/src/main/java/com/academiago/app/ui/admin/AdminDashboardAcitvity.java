package com.academiago.app.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.R;
import com.academiago.app.ui.auth.LoginActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        TextView welcomeText = findViewById(R.id.welcomeText);

        // Personalized greeting using stored email
        String email = getSharedPreferences("auth", MODE_PRIVATE).getString("email", "Admin");
        welcomeText.setText("Welcome, " + email + "!");

        Button createUserBtn = findViewById(R.id.createUserBtn);
        Button reportsBtn = findViewById(R.id.reportsBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);

        createUserBtn.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, AdminCreateUserActivity.class))
        );

        reportsBtn.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, ReportsActivity.class))
        );

        logoutBtn.setOnClickListener(v -> {
            getSharedPreferences("auth", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();

            startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
