package com.openclassrooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}

