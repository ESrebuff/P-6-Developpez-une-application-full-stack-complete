package com.openclassrooms.mddapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.CommentRequestDto;
import com.openclassrooms.mddapi.dto.CommentResponseDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing comments in the application.
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new comment based on the provided CommentRequestDto.
     *
     * @param commentRequestDto The CommentRequestDto containing comment details.
     * @return CommentResponseDto representing the created comment.
     */
    public CommentResponseDto create(CommentRequestDto commentRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment newComment = new Comment();
        newComment.setContent(commentRequestDto.getContent());
        newComment.setArticle(articleRepository.findById(commentRequestDto.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article not found")));

        newComment.setAuthor(user);

        Comment savedComment = commentRepository.save(newComment);
        return modelMapper.map(savedComment, CommentResponseDto.class);
    }
}
