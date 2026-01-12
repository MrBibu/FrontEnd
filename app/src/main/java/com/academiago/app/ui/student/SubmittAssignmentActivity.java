package com.academiago.app.ui.student;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.R;
import com.academiago.app.models.SubmissionDTO;
import com.academiago.app.network.repo.StudentRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitAssignmentActivity extends AppCompatActivity {
    private EditText assignmentIdInput, notesInput, fileUrlInput;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_assignment);

        assignmentIdInput = findViewById(R.id.assignmentIdInput);
        notesInput = findViewById(R.id.notesInput);
        fileUrlInput = findViewById(R.id.fileUrlInput);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(v -> {
            int assignmentId;
            try {
                assignmentId = Integer.parseInt(assignmentIdInput.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid assignment ID", Toast.LENGTH_SHORT).show();
                return;
            }

            String notes = notesInput.getText().toString().trim();
            String fileUrl = fileUrlInput.getText().toString().trim();

            SubmissionDTO dto = new SubmissionDTO(assignmentId, notes, fileUrl);
            StudentRepository repo = new StudentRepository(this);

            repo.submitAssignment(dto, new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SubmitAssignmentActivity.this, "Submission successful!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SubmitAssignmentActivity.this, "Submission failed: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(SubmitAssignmentActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
