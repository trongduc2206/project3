package com.project.recruitment.repository;

import com.project.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    List<Recruitment> findByStatus(int status);
}
