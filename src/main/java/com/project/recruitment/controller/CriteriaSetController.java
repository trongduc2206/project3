package com.project.recruitment.controller;

import com.project.recruitment.controller.request.CriteriaSetRequest;
import com.project.recruitment.controller.response.ResultResponse;
import com.project.recruitment.controller.response.CriteriaSetResponse;
import com.project.recruitment.entity.CriteriaSet;
import com.project.recruitment.service.CriteriaSetService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/criteria")
public class CriteriaSetController extends BaseController{
    private CriteriaSetService criteriaSetService;

    public CriteriaSetController(CriteriaSetService criteriaSetService){
        this.criteriaSetService = criteriaSetService;
    }
    @GetMapping("/{id}")
    public CriteriaSetResponse getById(@PathVariable Integer id){
        CriteriaSet criteriaSet = criteriaSetService.findById(id);
        CriteriaSetResponse criteriaSetResponse = new CriteriaSetResponse();
        if(criteriaSet != null){
            List<CriteriaSet> criteriaSets = new ArrayList<>();
            criteriaSets.add(criteriaSet);
            criteriaSetResponse.setData(criteriaSets);
            return success(criteriaSetResponse);
        } else {
            return error(criteriaSetResponse, "Not found criteria set");
        }
    }

    @GetMapping("/department/{id}")
    public CriteriaSetResponse getByDepartmentId(@PathVariable int id){
        List<CriteriaSet> criteriaSets = criteriaSetService.findByDepartmentId(id);
        CriteriaSetResponse criteriaSetResponse = new CriteriaSetResponse();
        if(criteriaSets!=null){
            criteriaSetResponse.setData(criteriaSets);
            return success(criteriaSetResponse);
        } else {
            criteriaSetResponse.setData(null);
            return error(criteriaSetResponse, "Not found department");
        }
    }

    @PostMapping
    public ResultResponse create(@RequestBody CriteriaSetRequest criteriaSetRequest){
        return success(criteriaSetService.createCriteriaSet(criteriaSetRequest));
    }

    @PutMapping
    public ResultResponse update(@RequestBody CriteriaSet criteriaSet){
        return success(criteriaSetService.update(criteriaSet));
    }

    @DeleteMapping("/{id}")
    public ResultResponse delete(@PathVariable int id){
        return success(criteriaSetService.delete(id));
    }

}
