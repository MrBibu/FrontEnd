uploadBtn.setOnClickListener(v -> {
String title = titleInput.getText().toString().trim();
String description = descriptionInput.getText().toString().trim();
String deadline = deadlineInput.getText().toString().trim();

    if (title.isEmpty() || description.isEmpty() || deadline.isEmpty()) {
        Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        return;
                }

AssignmentDTO dto = new AssignmentDTO(title, description, deadline);
TeacherRepository repo = new TeacherRepository(this);

    repo.uploadAssignment(dto, new retrofit2.Callback<Void>() {
    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
            Toast.makeText(UploadAssignmentActivity.this, "Assignment uploaded!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(UploadAssignmentActivity.this, "Upload failed: " + response.code(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(UploadAssignmentActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
        });
