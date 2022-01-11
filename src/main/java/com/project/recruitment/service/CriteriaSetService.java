package com.project.recruitment.service;

import com.project.recruitment.controller.request.CriteriaSetRequest;
import com.project.recruitment.controller.response.ResultResponse;
import com.project.recruitment.entity.CriteriaSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CriteriaSetService {
    ResultResponse createCriteriaSet(CriteriaSetRequest criteriaSetRequest);
    List<CriteriaSet> findByDepartmentId(Integer id);
    CriteriaSet findById(Integer id);
    ResultResponse update(CriteriaSet criteriaSet);
    ResultResponse delete(Integer id);
}
