package com.project.recruitment.service;

import com.project.recruitment.entity.Recruitment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruitmentService {
    Recruitment getCurrentRecruitment();

    void startNewRecruitment(Recruitment recruitment);

    List<Recruitment> getClosedRecruitment();

    List<Recruitment> getAllRecruitment();

    Recruitment getById(Integer id);

    void updateCurrentRecruitment(String information);
}
