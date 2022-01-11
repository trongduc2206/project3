package com.project.recruitment.repository;

import com.project.recruitment.entity.CriteriaSet;
import com.project.recruitment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriteriaSetRepository extends JpaRepository<CriteriaSet, Integer> {
    List<CriteriaSet> findCriteriaSetByDepartment(Department department);
}
