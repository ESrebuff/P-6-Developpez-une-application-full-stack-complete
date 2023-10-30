package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.Map;

import com.openclassrooms.mddapi.dto.SubjectDto;

public interface SubjectServiceI {

    Map<String, List<SubjectDto>> getSubjects();

    SubjectDto getSubject(Long id);

}
