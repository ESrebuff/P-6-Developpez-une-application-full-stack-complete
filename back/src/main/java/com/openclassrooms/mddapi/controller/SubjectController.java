package com.openclassrooms.mddapi.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.service.SubjectService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.JwtService;

@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Map<String, String>> createSubject(HttpServletRequest request,
            @ModelAttribute("subjects") SubjectDto subjectsDto) throws IllegalStateException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        if (token != null) {
            String email = jwtService.getUsernameFromToken(token);
            User userRepos = userRepository.findByUsername(email).orElse(null);
            Long authorId = userRepos.getId();
            Subject subject = new Subject();
            subject.setName(subjectsDto.getName());
            subjectService.create(subject, authorId);
            return ResponseEntity.ok(Map.of("message", "Subject created !"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
