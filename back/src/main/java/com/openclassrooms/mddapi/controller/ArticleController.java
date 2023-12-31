package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.ArticleRequestDto;
import com.openclassrooms.mddapi.dto.ArticleResponseDto;
import com.openclassrooms.mddapi.service.ArticleService;

import lombok.RequiredArgsConstructor;

/**
 * Controller class for managing articles in the application.
 */
@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * Handles the creation of a new article.
     *
     * @param articleRequestDto The request body containing article details.
     * @return ResponseEntity with the created ArticleDto.
     */
    @PostMapping()
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleRequestDto articleRequestDto) {
        ArticleDto createdArticle = articleService.create(articleRequestDto);
        return ResponseEntity.ok(createdArticle);
    }

    /**
     * Handles the retrieval of all articles.
     *
     * @return ResponseEntity with a list of ArticleResponseDto.
     */
    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() {
        List<ArticleResponseDto> articles = articleService.getAllArticlesWithDetails();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    /**
     * Handles the retrieval of an article by its ID.
     *
     * @param id The ID of the article to retrieve.
     * @return ResponseEntity with the ArticleResponseDto if found, or NOT_FOUND if
     *         not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Integer id) {
        ArticleResponseDto article = articleService.getArticleById(id);
        if (article != null) {
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
