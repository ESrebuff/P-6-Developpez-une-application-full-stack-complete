package com.openclassrooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
}
