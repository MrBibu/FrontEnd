package com.academiago.mobile.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.academiago.mobile.R;
import com.academiago.mobile.utils.TokenManager;

public class DashBoard extends AppCompatActivity {

    private TextView roleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        roleTextView = findViewById(R.id.roleTextView);

        String role = TokenManager.getRole();
        if (role != null) {
            roleTextView.setText("Welcome " + role);
            setupDashboardForRole(role);
        }
    }

    private void setupDashboardForRole(String role) {
        switch (role) {
            case "ADMIN":
                // Setup admin features
                break;
            case "TEACHER":
                // Setup teacher features
                break;
            case "STUDENT":
                // Setup student features
                break;
        }
    }
}
