package com.studyshare.repository;

import com.studyshare.entity.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long> {
    List<StudyMaterial> findAllByOrderByUploadedAtDesc();
    List<StudyMaterial> findByTitleContainingIgnoreCaseOrSubjectContainingIgnoreCase(String title, String subject);
}