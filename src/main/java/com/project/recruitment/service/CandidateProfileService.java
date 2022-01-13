package com.project.recruitment.service;

import com.project.recruitment.controller.request.CandidateProfileRequest;
import com.project.recruitment.controller.response.ResultResponse;
import com.project.recruitment.entity.CandidateProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateProfileService {
    List<CandidateProfile> getAll();
    CandidateProfile getById(Integer id);
    List<CandidateProfile> getByRecruitment(Integer recruitmentId);
    List<CandidateProfile> getByDepartment(Integer departmentId);
    ResultResponse create(CandidateProfileRequest candidateProfileRequest);
    ResultResponse update(CandidateProfile candidateProfile);
    ResultResponse delete(Integer id);
}
