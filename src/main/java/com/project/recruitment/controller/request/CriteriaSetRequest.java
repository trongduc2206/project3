package com.project.recruitment.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CriteriaSetRequest {
    private int status;

    private String name;

    private int weight;

    @JsonProperty("department_id")
    private int departmentId;
}
