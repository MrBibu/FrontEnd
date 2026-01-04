package com.academiago.app.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome to Teacher Dashboard!");
    }
}
