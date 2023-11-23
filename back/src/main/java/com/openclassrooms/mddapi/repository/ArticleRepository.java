package com.openclassrooms.mddapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.openclassrooms.mddapi.model.Article;

public interface ArticleRepository  extends JpaRepository<Article, Integer> {
    
    @Query("SELECT a FROM Article a LEFT JOIN FETCH a.comments WHERE a.id = :id")
    Optional<Article> findByIdWithComments(@Param("id") Integer id);
    
}
