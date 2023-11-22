package com.openclassrooms.mddapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.ArticleRequestDto;
import com.openclassrooms.mddapi.dto.ArticleResponseDto;
import com.openclassrooms.mddapi.dto.SubjectDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Subject;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService implements ArticleServiceI {
    
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final SubjectService subjectService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    
    @PostConstruct
    public void configureModelMapper() {
        modelMapper.createTypeMap(Article.class, ArticleDto.class)
                .addMapping(src -> src.getAuthor().getId(), ArticleDto::setAuthorId)
                .addMapping(src -> src.getSubject().getId(), ArticleDto::setSubjectId);

    }

    private ArticleDto mapToArticleDto(Article article) {
        return modelMapper.map(article, ArticleDto.class);
    }

    public ArticleDto create(ArticleRequestDto articleRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subject subject = modelMapper.map(subjectService.getSubject(articleRequestDto.getSubjectId()), Subject.class);
        if (subject == null) {
            throw new RuntimeException("Subject not found");
        }

        Article newArticle = new Article();
        newArticle.setTitle(articleRequestDto.getTitle());
        newArticle.setContent(articleRequestDto.getContent());
        newArticle.setSubject(subject);
        newArticle.setAuthor(user);

        Article savedArticle = articleRepository.save(newArticle);
        return mapToArticleDto(savedArticle);
    }
    
    public List<ArticleResponseDto> getAllArticlesWithDetails() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleResponseDto> articleResponseDtos = new ArrayList<>();

        for (Article article : articles) {
            ArticleResponseDto articleResponseDto = mapArticleToDto(article);
            articleResponseDtos.add(articleResponseDto);
        }

        return articleResponseDtos;
    }

    public ArticleResponseDto getArticleById(Integer id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            return mapArticleToDto(article);
        } else {
            throw new RuntimeException("Article not found");
        }
    }

    private ArticleResponseDto mapArticleToDto(Article article) {
        
        SubjectDto subjectDto = subjectService.getSubject(article.getSubject().getId());
        UserDto userDto = userService.getUserById(article.getAuthor().getId());

        return modelMapper.map(article, ArticleResponseDto.class)
                .setAuthorName(userDto.getName())
                .setSubjectName(subjectDto.getName());
    }
}
