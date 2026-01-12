package com.academiago.app.ui.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.R;
import com.academiago.app.models.RegisterRequest;
import com.academiago.app.models.Users;
import com.academiago.app.network.ApiClient;
import com.academiago.app.network.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCreateUserActivity extends AppCompatActivity {
    private EditText nameInput, emailInput, facultyInput, semesterInput;
    private Spinner roleSpinner;
    private Button createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_user);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        facultyInput = findViewById(R.id.facultyInput);
        semesterInput = findViewById(R.id.semesterInput);
        roleSpinner = findViewById(R.id.roleSpinner);
        createBtn = findViewById(R.id.createBtn);

        createBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String faculty = facultyInput.getText().toString().trim();
            String semester = semesterInput.getText().toString().trim();
            String role = roleSpinner.getSelectedItem().toString();

            if (name.isEmpty() || email.isEmpty() || role.isEmpty()) {
                Toast.makeText(this, "Name, email, and role are required", Toast.LENGTH_SHORT).show();
                return;
            }

            String token = getSharedPreferences("auth", MODE_PRIVATE).getString("jwt", null);
            if (token == null) {
                Toast.makeText(this, "Missing token. Please log in again.", Toast.LENGTH_SHORT).show();
                return;
            }

            AuthService service = ApiClient.getClient().create(AuthService.class);
            RegisterRequest req = new RegisterRequest(name, email, faculty, semester, role);

            service.createUser("Bearer " + token, req).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(AdminCreateUserActivity.this,
                                "User created: " + response.body().getEmail(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminCreateUserActivity.this, "Failed to create user", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(AdminCreateUserActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
