package com.academiago.app.models;

public class SubmissionDTO {
    private int assignmentId;
    private String notes;
    private String fileUrl; // optional if you handle file uploads separately

    public SubmissionDTO(int assignmentId, String notes, String fileUrl) {
        this.assignmentId = assignmentId;
        this.notes = notes;
        this.fileUrl = fileUrl;
    }

    public int getAssignmentId() { return assignmentId; }
    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
}
