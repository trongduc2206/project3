package com.project.recruitment.controller.response;

import com.project.recruitment.entity.Recruitment;
import lombok.Data;

@Data
public class CurrentRecruitmentResponse extends GeneralResponse {
    private Recruitment recruitment;
}
