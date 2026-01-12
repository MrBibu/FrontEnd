package com.academiago.app.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.ui.student.StudentDashboardActivity;
import com.academiago.app.ui.teacher.TeacherDashboardActivity;
import com.academiago.app.ui.admin.AdminDashboardActivity;
import com.academiago.app.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
        String jwt = prefs.getString("jwt", null);
        String role = prefs.getString("role", null);

        Intent intent;
        if (jwt != null && role != null) {
            switch (role.toLowerCase()) {
                case "student":
                    intent = new Intent(this, StudentDashboardActivity.class);
                    break;
                case "teacher":
                    intent = new Intent(this, TeacherDashboardActivity.class);
                    break;
                case "admin":
                    intent = new Intent(this, AdminDashboardActivity.class);
                    break;
                default:
                    intent = new Intent(this, LoginActivity.class);
            }
        } else {
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
