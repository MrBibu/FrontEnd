package com.academiago.app.network;

import com.academiago.app.models.AssignmentDTO;
import com.academiago.app.models.SubmissionDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentApi {
    @GET("/student/assignments")
    Call<List<AssignmentDTO>> getAssignments();

    @POST("/student/submissions")
    Call<Void> submitAssignment(@Body SubmissionDTO submission);
}
