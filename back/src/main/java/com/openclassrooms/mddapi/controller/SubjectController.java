package com.openclassrooms.mddapi.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.mddapi.service.SubjectService;
import lombok.AllArgsConstructor;
import com.openclassrooms.mddapi.dto.SubjectDto;

@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public Map<String, List<SubjectDto>> getSubjects() {
        return subjectService.getSubjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubject(@PathVariable Long id) {
        SubjectDto subjectsDto = subjectService.getSubject(id);
        if (subjectsDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subjectsDto);
    }

    // @PostMapping
    // public ResponseEntity<Map<String, String>> createSubject(HttpServletRequest
    // request,
    // @ModelAttribute("subjects") SubjectDto subjectsDto) throws
    // IllegalStateException, IOException {

    // String authorizationHeader = request.getHeader("Authorization");
    // String token = null;
    // if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
    // {
    // token = authorizationHeader.substring(7);
    // }
    // if (token != null) {
    // String email = jwtService.getUsernameFromToken(token);
    // User userRepos = userRepository.findByUsername(email).orElse(null);
    // Long authorId = userRepos.getId();
    // Subject subject = new Subject();
    // subject.setName(subjectsDto.getName());
    // subjectService.create(subject, authorId);
    // return ResponseEntity.ok(Map.of("message", "Subject created !"));
    // } else {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    // }

    // }
}
