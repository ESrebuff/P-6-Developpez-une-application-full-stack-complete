package com.openclassrooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
