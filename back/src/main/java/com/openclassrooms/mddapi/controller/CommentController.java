package com.openclassrooms.mddapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.CommentRequestDto;
import com.openclassrooms.mddapi.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        CommentDto createdComment = commentService.create(commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
}
