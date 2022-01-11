package com.project.recruitment.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class CandidateProfileRequest {
    @JsonProperty("recruitment_status")
    private int recruitmentStatus;

    private String name;

    private String gender;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("identity_card")
    private String identityCard;

    private String phone;

    private String description;

    @JsonProperty("department_id")
    private Integer departmentId;

    @JsonProperty("recruitment_id")
    private Integer recruitmentId;


}
