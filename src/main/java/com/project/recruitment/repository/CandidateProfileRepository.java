package com.project.recruitment.repository;

import com.project.recruitment.entity.CandidateProfile;
import com.project.recruitment.entity.Department;
import com.project.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Integer> {
    List<CandidateProfile> findByRecruitment(Recruitment recruitment);

    List<CandidateProfile> findByDepartment(Department department);

    List<CandidateProfile> findByPointGreaterThan(Double point);
}
