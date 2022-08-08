package com.example.thymeleaf.repository;

import com.example.thymeleaf.entity.StudentCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCVRepository extends JpaRepository<StudentCV, Long> {
}