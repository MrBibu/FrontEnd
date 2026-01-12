package com.academiago.app.network;

import com.academiago.app.models.AssignmentDTO;
import com.academiago.app.models.NoticeDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TeacherApi {
    @POST("/teacher/assignments")
    Call<Void> uploadAssignment(@Body AssignmentDTO assignment);

    @POST("/teacher/notices")
    Call<Void> publishNotice(@Body NoticeDTO notice);
}
