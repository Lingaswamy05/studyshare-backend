package com.studyshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "study_materials")
public class StudyMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String title;

    @NotBlank
    @Size(max = 80)
    private String subject;

    @NotNull
    private Integer semester;

    @Size(max = 500)
    @Column(length = 500)
    private String description;

    @Size(max = 80)
    private String uploadedBy;

    @NotBlank
    @Size(max = 1000)
    @Column(length = 1000)
    private String fileUrl;

    private LocalDate uploadedAt;

    @PrePersist
    public void beforeInsert() {
        if (uploadedAt == null) uploadedAt = LocalDate.now();
        if (uploadedBy == null || uploadedBy.isBlank()) uploadedBy = "Student";
        if (description == null) description = "";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public LocalDate getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDate uploadedAt) { this.uploadedAt = uploadedAt; }
}