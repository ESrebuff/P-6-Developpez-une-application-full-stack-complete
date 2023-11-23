package com.openclassrooms.mddapi.service;

import java.util.List;
import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.ArticleRequestDto;
import com.openclassrooms.mddapi.dto.ArticleResponseDto;

public interface ArticleServiceI {

    ArticleDto create(ArticleRequestDto articleRequestDto);

    List<ArticleResponseDto> getAllArticlesWithDetails();

    ArticleResponseDto getArticleById(Integer id);

}
