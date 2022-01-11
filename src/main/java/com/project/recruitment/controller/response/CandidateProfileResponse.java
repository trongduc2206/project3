package com.project.recruitment.controller.response;

import com.project.recruitment.entity.CandidateProfile;
import lombok.Data;

import java.util.List;

@Data
public class CandidateProfileResponse extends GeneralResponse{
    private List<CandidateProfile> data;
}
