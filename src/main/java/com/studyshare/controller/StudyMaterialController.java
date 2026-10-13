
package com.studyshare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.studyshare.entity.StudyMaterial;
import com.studyshare.repository.StudyMaterialRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/materials")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://127.0.0.1:5173"
})
public class StudyMaterialController {

    private final StudyMaterialRepository repository;

    public StudyMaterialController(StudyMaterialRepository repository) {
        this.repository = repository;
    }

    // Test endpoint
    @GetMapping("/test")
    public String test() {
        return "StudyShare API is working!";
    }

    // Get all materials or search by keyword
    @GetMapping
    public List<StudyMaterial> getAll(
            @RequestParam(required = false) String keyword) {

        if (keyword != null && !keyword.isBlank()) {
            return repository
                    .findByTitleContainingIgnoreCaseOrSubjectContainingIgnoreCase(
                            keyword, keyword);
        }

        return repository.findAllByOrderByUploadedAtDesc();
    }

    // Get a material by ID
    @GetMapping("/{id}")
    public StudyMaterial getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Material not found"));
    }

    // Add a new material
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudyMaterial create(
            @Valid @RequestBody StudyMaterial material) {

        material.setId(null);
        return repository.save(material);
    }

    // Update an existing material
    @PutMapping("/{id}")
    public StudyMaterial update(
            @PathVariable Long id,
            @Valid @RequestBody StudyMaterial input) {

        StudyMaterial material = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Material not found"));

        material.setTitle(input.getTitle());
        material.setSubject(input.getSubject());
        material.setSemester(input.getSemester());
        material.setDescription(input.getDescription());
        material.setUploadedBy(input.getUploadedBy());
        material.setFileUrl(input.getFileUrl());

        return repository.save(material);
    }

    // Delete a material
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Material not found");
        }

        repository.deleteById(id);
    }
}
