package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.Map;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.model.Subject;

public interface SubjectServiceI {

    SubjectDto create(Subject subject, Long authorId);

    Map<String, List<SubjectDto>> getSubjects();

    SubjectDto getSubject(Long id);

}
