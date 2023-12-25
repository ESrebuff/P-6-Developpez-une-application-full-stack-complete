package com.openclassrooms.mddapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.CommentRequestDto;
import com.openclassrooms.mddapi.dto.CommentResponseDto;
import com.openclassrooms.mddapi.service.CommentService;

import lombok.RequiredArgsConstructor;

/**
 * Controller class for managing comments in the application.
 */
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Handles the creation of a new comment.
     *
     * @param commentRequestDto The request body containing comment details.
     * @return ResponseEntity with the created CommentResponseDto and HTTP status
     *         CREATED.
     */
    @PostMapping()
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto createdComment = commentService.create(commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
}
