package com.academiago.app.ui.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.R;
import com.academiago.app.ui.auth.LoginActivity;

public class TeacherDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        TextView welcomeText = findViewById(R.id.welcomeText);

        // Personalized greeting using stored email
        String email = getSharedPreferences("auth", MODE_PRIVATE).getString("email", "Teacher");
        welcomeText.setText("Welcome, " + email + "!");

        // Buttons for teacher features
        Button uploadAssignmentBtn = findViewById(R.id.uploadAssignmentBtn);
        Button publishNoticeBtn = findViewById(R.id.publishNoticeBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);

        uploadAssignmentBtn.setOnClickListener(v ->
                startActivity(new Intent(this, UploadAssignmentActivity.class))
        );

        publishNoticeBtn.setOnClickListener(v ->
                startActivity(new Intent(this, PublishNoticeActivity.class))
        );

        logoutBtn.setOnClickListener(v -> {
            getSharedPreferences("auth", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
