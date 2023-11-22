package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

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
    private String authorName;
    private String subjectName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleResponseDto setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public ArticleResponseDto setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }
}
