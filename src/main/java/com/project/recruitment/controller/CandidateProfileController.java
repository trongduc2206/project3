package com.project.recruitment.controller;

import com.project.recruitment.controller.request.CandidateProfileRequest;
import com.project.recruitment.controller.response.CandidateProfileResponse;
import com.project.recruitment.controller.response.ResultResponse;
import com.project.recruitment.entity.CandidateProfile;
import com.project.recruitment.service.CandidateProfileService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateProfileController extends BaseController{
    private CandidateProfileService candidateProfileService;

    public CandidateProfileController(CandidateProfileService candidateProfileService){
        this.candidateProfileService = candidateProfileService;
    }

    @GetMapping
    public CandidateProfileResponse getAll(){
        List<CandidateProfile> candidateProfiles = candidateProfileService.getAll();
        CandidateProfileResponse candidateProfileResponse = new CandidateProfileResponse();
        candidateProfileResponse.setData(candidateProfiles);
        return success(candidateProfileResponse);
    }

    @GetMapping("/{id}")
    public CandidateProfileResponse getById(@PathVariable Integer id){
        CandidateProfile candidateProfile = candidateProfileService.getById(id);
        CandidateProfileResponse candidateProfileResponse = new CandidateProfileResponse();
        if(candidateProfile != null){
            List<CandidateProfile> candidateProfiles = new ArrayList<>();
            candidateProfiles.add(candidateProfile);
            candidateProfileResponse.setData(candidateProfiles);
            return success(candidateProfileResponse);
        } else {
            return error(candidateProfileResponse, "Not found candidate with id "+id);
        }
    }

    @GetMapping("/recruitment/{id}")
    public CandidateProfileResponse getByRecruitment(@PathVariable int id){
        List<CandidateProfile> candidateProfiles = candidateProfileService.getByRecruitment(id);
        CandidateProfileResponse candidateProfileResponse = new CandidateProfileResponse();
        candidateProfileResponse.setData(candidateProfiles);
        return success(candidateProfileResponse);
    }

    @GetMapping("/department/{id}")
    public CandidateProfileResponse getByDepartment(@PathVariable int id){
        List<CandidateProfile> candidateProfiles = candidateProfileService.getByDepartment(id);
        CandidateProfileResponse candidateProfileResponse = new CandidateProfileResponse();
        candidateProfileResponse.setData(candidateProfiles);
        return success(candidateProfileResponse);
    }

    @PostMapping
    public ResultResponse create(@RequestBody CandidateProfileRequest candidateProfileRequest){
        return success(candidateProfileService.create(candidateProfileRequest));
    }

    @PutMapping
    public ResultResponse update(@RequestBody CandidateProfile candidateProfile){
        return success(candidateProfileService.update(candidateProfile));
    }

    @DeleteMapping("/{id}")
    public ResultResponse delete(@PathVariable Integer id){
        return success(candidateProfileService.delete(id));
    }
}
