package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.ArticleRequestDto;

public interface ArticleServiceI {

    ArticleDto create(ArticleRequestDto articleRequestDto);

}
