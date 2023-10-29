package com.openclassrooms.mddapi.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.exception.SubjectNotFoundException;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.repository.SubjectRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectService implements SubjectServiceI {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @PostConstruct
    public void configureModelMapper() {
        modelMapper.createTypeMap(Subject.class, SubjectDto.class)
                .addMapping(Subject::getCreatedAt, SubjectDto::setCreated_at)
                .addMapping(Subject::getUpdatedAt, SubjectDto::setUpdated_at);
    }

    private SubjectDto mapToSubjectDto(Subject subject) {
        return modelMapper.map(subject, SubjectDto.class);
    }

    @Override
    public SubjectDto create(Subject subject, Long userId) {
        subject.setAuthorId(userId);
        Subject subjectSaved = subjectRepository.save(subject);
        SubjectDto subjectDto = mapToSubjectDto(subjectSaved);
        return subjectDto;
    }

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

    @Override
    public SubjectDto getSubject(Long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        if (subject != null) {
            return mapToSubjectDto(subject);
        }
        return null;
    }

}