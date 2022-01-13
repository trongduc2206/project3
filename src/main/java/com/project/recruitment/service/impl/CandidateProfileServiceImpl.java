package com.project.recruitment.service.impl;

import com.project.recruitment.controller.request.CandidateProfileRequest;
import com.project.recruitment.controller.response.ResultResponse;
import com.project.recruitment.entity.CandidateProfile;
import com.project.recruitment.entity.Department;
import com.project.recruitment.entity.Recruitment;
import com.project.recruitment.repository.CandidateProfileRepository;
import com.project.recruitment.repository.DepartmentRepository;
import com.project.recruitment.repository.RecruitmentRepository;
import com.project.recruitment.service.CandidateProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateProfileServiceImpl implements CandidateProfileService{
    @Autowired
    private CandidateProfileRepository candidateProfileRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    public CandidateProfileServiceImpl(CandidateProfileRepository candidateProfileRepository
    , DepartmentRepository departmentRepository, RecruitmentRepository recruitmentRepository){
        this.candidateProfileRepository = candidateProfileRepository;
        this.departmentRepository = departmentRepository;
        this.recruitmentRepository= recruitmentRepository;
    }

    @Override
    public List<CandidateProfile> getAll() {
        return candidateProfileRepository.findAll();
    }

    @Override
    public CandidateProfile getById(Integer id) {
        Optional<CandidateProfile> candidateProfileOptional = candidateProfileRepository.findById(id);
        if(candidateProfileOptional.isPresent()){
            return candidateProfileOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public List<CandidateProfile> getByRecruitment(Integer recruitmentId) {
        Optional<Recruitment> recruitmentOptional = recruitmentRepository.findById(recruitmentId);
        if(recruitmentOptional.isPresent()){
            List<CandidateProfile> candidateProfiles = candidateProfileRepository.findByRecruitment(recruitmentOptional.get());
            return candidateProfiles;
        } else {
            return null;
        }
    }

    @Override
    public List<CandidateProfile> getByDepartment(Integer departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if(departmentOptional.isPresent()){
            List<CandidateProfile> candidateProfiles = candidateProfileRepository.findByDepartment(departmentOptional.get());
            return candidateProfiles;
        } else {
            return null;
        }
    }

    @Override
    public ResultResponse create(CandidateProfileRequest candidateProfileRequest) {
        ResultResponse resultResponse = new ResultResponse();
        if(candidateProfileRequest.getRecruitmentId() == null || candidateProfileRequest.getDepartmentId() == null){
            resultResponse.setResult(false);
            resultResponse.setMessage("Recruitment ID and Department ID must not be null");
            return resultResponse;
        }
        Optional<Recruitment> recruitmentOptional = recruitmentRepository.findById(candidateProfileRequest.getRecruitmentId());
        Optional<Department> department = departmentRepository.findById(candidateProfileRequest.getDepartmentId());
        if(recruitmentOptional.isPresent() && department.isPresent()){
            CandidateProfile candidateProfile = new CandidateProfile();
            candidateProfile.setRecruitmentStatus(candidateProfileRequest.getRecruitmentStatus());
            candidateProfile.setName(candidateProfileRequest.getName());
            candidateProfile.setGender(candidateProfileRequest.getGender());
            candidateProfile.setDateOfBirth(candidateProfileRequest.getDateOfBirth());
            candidateProfile.setIdentityCard(candidateProfileRequest.getIdentityCard());
            candidateProfile.setPhone(candidateProfileRequest.getPhone());
            candidateProfile.setDescription(candidateProfileRequest.getDescription());
            candidateProfile.setRecruitment(recruitmentOptional.get());
            candidateProfile.setDepartment(department.get());
            candidateProfileRepository.save(candidateProfile);
            resultResponse.setResult(true);
        } else {
            resultResponse.setResult(false);
            resultResponse.setMessage("Not found department or recruitment");
        }
            return resultResponse;
    }

    @Override
    public ResultResponse update(CandidateProfile candidateProfile) {
        ResultResponse resultResponse = new ResultResponse();
        if(candidateProfile.getId() == null){
            resultResponse.setResult(false);
            resultResponse.setMessage("candidate profile id must not be null");
            return resultResponse;
        }
        Optional<CandidateProfile> candidateProfileOptional = candidateProfileRepository.findById(candidateProfile.getId());
        if(candidateProfileOptional.isPresent()){
                CandidateProfile candidateProfileOld = candidateProfileOptional.get();
                if(Integer.valueOf(candidateProfile.getRecruitmentStatus()) != null){
                    candidateProfileOld.setRecruitmentStatus(candidateProfile.getRecruitmentStatus());
                }
                if(candidateProfile.getName() != null){
                    candidateProfileOld.setName(candidateProfile.getName());
                }
                if(candidateProfile.getGender() != null){
                    candidateProfileOld.setGender(candidateProfile.getGender());
                }
                if(candidateProfile.getDateOfBirth() != null){
                    candidateProfileOld.setDateOfBirth(candidateProfile.getDateOfBirth());
                }
                if(candidateProfile.getIdentityCard() != null){
                    candidateProfileOld.setIdentityCard(candidateProfile.getIdentityCard());
                }
                if(candidateProfile.getPhone() != null){
                    candidateProfileOld.setPhone(candidateProfile.getPhone());
                }
                if(candidateProfile.getDescription() != null){
                    candidateProfileOld.setDescription(candidateProfile.getDescription());
                }
                candidateProfileRepository.save(candidateProfileOld);
                resultResponse.setResult(true);
        } else {
                resultResponse.setResult(false);
                resultResponse.setMessage("Not found candidate profile with id "+candidateProfile.getIdentityCard());
        }
        return resultResponse;
    }

    @Override
    public ResultResponse delete(Integer id) {
        ResultResponse resultResponse = new ResultResponse();
        Optional<CandidateProfile> candidateProfileOptional = candidateProfileRepository.findById(id);
        if(candidateProfileOptional.isPresent()){
            candidateProfileRepository.deleteById(id);
            resultResponse.setResult(true);
        } else {
            resultResponse.setResult(false);
            resultResponse.setMessage("Not found candidate profile with id "+id);
        }
        return resultResponse;
    }
}
