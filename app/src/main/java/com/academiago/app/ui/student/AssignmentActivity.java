package com.academiago.app.ui.student;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.academiago.app.R;
import com.academiago.app.models.AssignmentDTO;
import com.academiago.app.network.repo.StudentRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentsActivity extends AppCompatActivity {
    private ListView assignmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        assignmentsList = findViewById(R.id.assignmentsList);

        StudentRepository repo = new StudentRepository(this);
        repo.getAssignments(new Callback<List<AssignmentDTO>>() {
            @Override
            public void onResponse(Call<List<AssignmentDTO>> call, Response<List<AssignmentDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AssignmentDTO> assignments = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            AssignmentsActivity.this,
                            android.R.layout.simple_list_item_1,
                            assignments.stream().map(a -> a.getTitle() + " - " + a.getDeadline()).toArray(String[]::new)
                    );
                    assignmentsList.setAdapter(adapter);
                } else {
                    Toast.makeText(AssignmentsActivity.this, "Failed to load assignments", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AssignmentDTO>> call, Throwable t) {
                Toast.makeText(AssignmentsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
