package com.academiago.app.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.R;
import com.academiago.app.ui.auth.LoginActivity;
import com.academiago.app.ui.student.AssignmentsActivity;
import com.academiago.app.ui.student.SubmitAssignmentActivity;
import com.academiago.app.ui.student.NoticesActivity;
import com.academiago.app.ui.student.GradesActivity;
import com.academiago.app.ui.student.MessagesActivity;

public class StudentDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        TextView welcomeText = findViewById(R.id.welcomeText);
        String email = getSharedPreferences("auth", MODE_PRIVATE).getString("email", "Student");
        welcomeText.setText("Welcome, " + email + "!");

        Button assignmentsBtn = findViewById(R.id.assignmentsBtn);
        Button submitBtn = findViewById(R.id.submitBtn);
        Button noticesBtn = findViewById(R.id.noticesBtn);
        Button gradesBtn = findViewById(R.id.gradesBtn);
        Button messagesBtn = findViewById(R.id.messagesBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);

        assignmentsBtn.setOnClickListener(v -> startActivity(new Intent(this, AssignmentsActivity.class)));
        submitBtn.setOnClickListener(v -> startActivity(new Intent(this, SubmitAssignmentActivity.class)));
        noticesBtn.setOnClickListener(v -> startActivity(new Intent(this, NoticesActivity.class)));
        gradesBtn.setOnClickListener(v -> startActivity(new Intent(this, GradesActivity.class)));
        messagesBtn.setOnClickListener(v -> startActivity(new Intent(this, MessagesActivity.class)));

        logoutBtn.setOnClickListener(v -> {
            getSharedPreferences("auth", MODE_PRIVATE).edit().clear().apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
