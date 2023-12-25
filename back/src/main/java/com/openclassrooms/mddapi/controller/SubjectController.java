package com.openclassrooms.mddapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.service.SubjectService;
import com.openclassrooms.mddapi.dto.SubjectDto;

import lombok.AllArgsConstructor;

/**
 * Controller class for managing subjects in the application.
 */
@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    /**
     * Retrieves all subjects.
     *
     * @return A map with subject categories as keys and lists of SubjectDto as
     *         values.
     */
    @GetMapping
    public Map<String, List<SubjectDto>> getSubjects() {
        return subjectService.getSubjects();
    }

    /**
     * Retrieves a specific subject by its ID.
     *
     * @param id The ID of the subject to retrieve.
     * @return ResponseEntity with the SubjectDto if the subject is found, otherwise
     *         returns NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubject(@PathVariable Integer id) {
        SubjectDto subjectsDto = subjectService.getSubject(id);
        if (subjectsDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subjectsDto);
    }

}
