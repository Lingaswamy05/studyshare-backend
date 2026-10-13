package com.studyshare.config;

import com.studyshare.entity.StudyMaterial;
import com.studyshare.repository.StudyMaterialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleData {
    @Bean
    CommandLineRunner seedMaterials(StudyMaterialRepository repository) {
        return args -> {
            if (repository.count() > 0) return;
            add(repository, "Java Programming — Quick Notes", "Java", 3,
                    "Core Java concepts, OOP principles, and common examples for revision.",
                    "StudyShare Team", "https://docs.oracle.com/javase/tutorial/");
            add(repository, "Database Management Systems", "DBMS", 4,
                    "Keys, normalization, SQL queries, and transaction concepts.",
                    "StudyShare Team", "https://www.postgresql.org/docs/");
            add(repository, "Operating Systems Revision Guide", "Operating Systems", 4,
                    "Process scheduling, deadlocks, memory management, and page replacement.",
                    "StudyShare Team", "https://pages.cs.wisc.edu/~remzi/OSTEP/");
        };
    }

    private void add(StudyMaterialRepository repository, String title, String subject, int semester,
                    String description, String uploadedBy, String fileUrl) {
        StudyMaterial m = new StudyMaterial();
        m.setTitle(title);
        m.setSubject(subject);
        m.setSemester(semester);
        m.setDescription(description);
        m.setUploadedBy(uploadedBy);
        m.setFileUrl(fileUrl);
        repository.save(m);
    }
}