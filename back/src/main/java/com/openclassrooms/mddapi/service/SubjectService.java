package com.openclassrooms.mddapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.repository.SubjectRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

/**
 * Service class for managing subjects in the application.
 */
@Service
@AllArgsConstructor
public class SubjectService implements SubjectServiceI {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    /**
     * Configures ModelMapper for mapping Subject to SubjectDto.
     */
    @PostConstruct
    public void configureModelMapper() {
        modelMapper.createTypeMap(Subject.class, SubjectDto.class)
                .addMapping(Subject::getCreatedAt, SubjectDto::setCreated_at)
                .addMapping(Subject::getUpdatedAt, SubjectDto::setUpdated_at);
    }

    /**
     * Retrieves all subjects and organizes them in a map.
     *
     * @return Map containing a list of SubjectDto objects under the key "subjects".
     */
    @Override
    public Map<String, List<SubjectDto>> getSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDto> subjectDtos = subjects.stream()
                .map(this::mapToSubjectDto)
                .collect(Collectors.toList());

        Map<String, List<SubjectDto>> response = new HashMap<>();
        response.put("subjects", subjectDtos);

        return response;
    }

    /**
     * Retrieves a subject by its ID.
     *
     * @param id The ID of the subject to retrieve.
     * @return SubjectDto representing the retrieved subject, or null if not found.
     */
    @Override
    public SubjectDto getSubject(Integer id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        if (subject != null) {
            return mapToSubjectDto(subject);
        }
        return null;
    }

    private SubjectDto mapToSubjectDto(Subject subject) {
        return modelMapper.map(subject, SubjectDto.class);
    }
}
