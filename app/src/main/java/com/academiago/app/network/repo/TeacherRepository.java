package com.academiago.app.network.repo;

import android.content.Context;

import com.academiago.app.models.AssignmentDTO;
import com.academiago.app.models.NoticeDTO;
import com.academiago.app.network.ApiClient;
import com.academiago.app.network.TeacherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherRepository {
    private final TeacherApi teacherApi;

    public TeacherRepository(Context context) {
        teacherApi = ApiClient.getClient(context).create(TeacherApi.class);
    }

    public void uploadAssignment(AssignmentDTO assignment, Callback<Void> callback) {
        teacherApi.uploadAssignment(assignment).enqueue(callback);
    }

    public void publishNotice(NoticeDTO notice, Callback<Void> callback) {
        teacherApi.publishNotice(notice).enqueue(callback);
    }
}
