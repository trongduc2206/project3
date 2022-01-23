package com.project.recruitment.service;

import com.project.recruitment.controller.response.LoginResponse;
import com.project.recruitment.controller.response.ResultResponse;
import com.project.recruitment.controller.response.UpdateAccountResponse;
import com.project.recruitment.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    LoginResponse findByUsernameAndPassword(String username, String password);
    ResultResponse insert(Account account);
    UpdateAccountResponse update(Account account);
    UpdateAccountResponse delete(Integer id);
}
