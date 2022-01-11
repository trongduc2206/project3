package com.project.recruitment.controller;

import com.project.recruitment.controller.response.CurrentRecruitmentResponse;
import com.project.recruitment.controller.response.GeneralResponse;
import com.project.recruitment.controller.response.RecruitmentResponse;
import com.project.recruitment.entity.Recruitment;
import com.project.recruitment.service.RecruitmentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recruitment")
public class RecruitmentController extends BaseController{
    public RecruitmentService recruitmentService;

    public RecruitmentController(RecruitmentService recruitmentService){
        this.recruitmentService = recruitmentService;
    }

    @GetMapping(value = "/current")
    public CurrentRecruitmentResponse getCurrentRecruitment(){
        Recruitment recruitment = recruitmentService.getCurrentRecruitment();
        CurrentRecruitmentResponse currentRecruitmentResponse = new CurrentRecruitmentResponse();
        currentRecruitmentResponse.setRecruitment(recruitment);
        return success(currentRecruitmentResponse);
    }

    @GetMapping(value = "/closed")
    public RecruitmentResponse getClosedRecruitment(){
        List<Recruitment> recruitmentList = recruitmentService.getClosedRecruitment();
        RecruitmentResponse recruitmentResponse = new RecruitmentResponse();
        recruitmentResponse.setData(recruitmentList);
        return success(recruitmentResponse);
    }

    @GetMapping(value = "/{id}")
    public RecruitmentResponse getById(@PathVariable Integer id){
        List<Recruitment> recruitments = new ArrayList<>();
        Recruitment recruitment = recruitmentService.getById(id);
        RecruitmentResponse recruitmentResponse = new RecruitmentResponse();
        if(recruitment != null){
            recruitments.add(recruitment);
            recruitmentResponse.setData(recruitments);
            return success(recruitmentResponse);
        } else {
            return error(recruitmentResponse, "Not found recruitment");
        }
    }


    @GetMapping()
    public RecruitmentResponse getAll(){
        RecruitmentResponse recruitmentResponse = new RecruitmentResponse();
        recruitmentResponse.setData(recruitmentService.getAllRecruitment());
        return success(recruitmentResponse);
    }

    @PostMapping
    public GeneralResponse startNewRecruitment(@RequestBody Recruitment recruitment){
        recruitmentService.startNewRecruitment(recruitment);
        return success();
    }

    @PutMapping
    public GeneralResponse updateCurrentRecruitment(@RequestBody String information){
        recruitmentService.updateCurrentRecruitment(information);
        return success();
    }

}
