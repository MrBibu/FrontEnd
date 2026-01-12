package com.academiago.app.network.repo;

import android.content.Context;

import com.academiago.app.models.AssignmentDTO;
import com.academiago.app.models.SubmissionDTO;
import com.academiago.app.network.ApiClient;
import com.academiago.app.network.StudentApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class StudentRepository {
    private final StudentApi studentApi;

    public StudentRepository(Context context) {
        studentApi = ApiClient.getClient(context).create(StudentApi.class);
    }

    public void getAssignments(Callback<List<AssignmentDTO>> callback) {
        studentApi.getAssignments().enqueue(callback);
    }

    public void submitAssignment(SubmissionDTO submission, Callback<Void> callback) {
        studentApi.submitAssignment(submission).enqueue(callback);
    }
}
