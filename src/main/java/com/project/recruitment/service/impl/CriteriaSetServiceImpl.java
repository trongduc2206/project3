package com.project.recruitment.service.impl;

import com.project.recruitment.controller.request.CriteriaSetRequest;
import com.project.recruitment.controller.response.ResultResponse;
import com.project.recruitment.entity.CriteriaSet;
import com.project.recruitment.entity.Department;
import com.project.recruitment.repository.CriteriaSetRepository;
import com.project.recruitment.repository.DepartmentRepository;
import com.project.recruitment.service.CriteriaSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CriteriaSetServiceImpl implements CriteriaSetService {
    @Autowired
    private CriteriaSetRepository criteriaSetRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public CriteriaSetServiceImpl(CriteriaSetRepository criteriaSetRepository, DepartmentRepository departmentRepository){
        this.criteriaSetRepository = criteriaSetRepository;
        this.departmentRepository = departmentRepository;
    }
    @Override
    public ResultResponse createCriteriaSet(CriteriaSetRequest criteriaSetRequest) {
        Optional<Department> department = departmentRepository.findById(criteriaSetRequest.getDepartmentId());
        ResultResponse resultResponse = new ResultResponse();
        if(department.isPresent()){
            CriteriaSet criteriaSet = new CriteriaSet();
            criteriaSet.setStatus(criteriaSetRequest.getStatus());
            criteriaSet.setName(criteriaSetRequest.getName());
            criteriaSet.setWeight(criteriaSetRequest.getWeight());
            criteriaSet.setDepartment(department.get());
            criteriaSetRepository.save(criteriaSet);
            resultResponse.setResult(true);
        } else {
            resultResponse.setResult(false);
            resultResponse.setMessage("Not found department for this criteria");
        }
        return resultResponse;
    }

    @Override
    public List<CriteriaSet> findByDepartmentId(Integer departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(department.isPresent()){
            List<CriteriaSet> criteriaSets = new ArrayList<>();
            criteriaSets = criteriaSetRepository.findCriteriaSetByDepartment(department.get());
            return criteriaSets;
        } else {
            return null;
        }
    }

    @Override
    public CriteriaSet findById(Integer id) {
        Optional<CriteriaSet> criteriaSetOptional = criteriaSetRepository.findById(id);
        if(criteriaSetOptional.isPresent()){
            return criteriaSetOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public ResultResponse update(CriteriaSet criteriaSet) {
        ResultResponse resultResponse = new ResultResponse();
        if(criteriaSet.getId() == null){
              resultResponse.setResult(false);
              resultResponse.setMessage("Criteria ID is null");
              return resultResponse;
          }
          Optional<CriteriaSet> criteriaSetOptional = criteriaSetRepository.findById(criteriaSet.getId());
          if(criteriaSetOptional.isPresent()){
              Optional<Department> department = departmentRepository.findById(criteriaSetOptional.get().getDepartment().getId());
              if(department.isPresent()){
                  criteriaSet.setDepartment(department.get());
                  criteriaSetRepository.save(criteriaSet);
                  resultResponse.setResult(true);
              } else {
                  resultResponse.setResult(false);
                  resultResponse.setMessage("Not found department for this criteria");
              }
          } else {
              resultResponse.setResult(false);
              resultResponse.setMessage("Not found criteria with id "+criteriaSet.getId());
          }
        return resultResponse;
    }

    @Override
    public ResultResponse delete(Integer id) {
        Optional<CriteriaSet> criteriaSetOptional = criteriaSetRepository.findById(id);
        ResultResponse resultResponse = new ResultResponse();
        if(criteriaSetOptional.isPresent()){
            criteriaSetRepository.deleteById(id);
            resultResponse.setResult(true);
        } else {
            resultResponse.setResult(false);
            resultResponse.setMessage("Not found criteria with id " + id);
        }
        return resultResponse;
    }
}
