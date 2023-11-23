package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDto {

    private Integer id;
    private String title;
    private String content;
    private UserDto author;
    private SubjectDto subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponseDto> comments;

    public ArticleResponseDto setAuthor(UserDto author) {
        this.author = author;
        return this;
    }

    public ArticleResponseDto setSubject(SubjectDto subject) {
        this.subject = subject;
        return this;
    }

    public ArticleResponseDto setComments(List<CommentResponseDto> comments) {
        this.comments = comments;
        return this;
    }
}
