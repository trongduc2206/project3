package com.project.recruitment.controller.response;

import com.project.recruitment.entity.CriteriaSet;
import lombok.Data;

import java.util.List;

@Data
public class CriteriaSetResponse extends GeneralResponse{
    List<CriteriaSet> data;
}
