package com.project.recruitment.controller.response;

import lombok.Data;

@Data
public class LoginResponse extends GeneralResponse {
    private Boolean result;

    private String role;
}
