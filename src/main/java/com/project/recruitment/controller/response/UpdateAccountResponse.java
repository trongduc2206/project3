package com.project.recruitment.controller.response;

import lombok.Data;

@Data
public class UpdateAccountResponse extends GeneralResponse{
    private Boolean result;
    private Integer id;
}
