package com.project.recruitment.controller.response;

import lombok.Data;

@Data
public class ResultResponse extends GeneralResponse{
    private Boolean result;
    private String message;
}
