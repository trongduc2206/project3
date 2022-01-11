package com.project.recruitment.controller;
import com.project.recruitment.controller.request.LoginRequest;
import com.project.recruitment.controller.response.GeneralResponse;
import com.project.recruitment.controller.response.LoginResponse;
import com.project.recruitment.controller.response.UpdateAccountResponse;
import com.project.recruitment.entity.Account;
import com.project.recruitment.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping(value = "/login")
    public LoginResponse checkAccount(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = accountService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        return success(loginResponse);
    }

    @PostMapping
    public GeneralResponse create(@RequestBody Account account){
        accountService.insert(account);
        return success();
    }

    @PutMapping
    public UpdateAccountResponse update(@RequestBody Account account){
        UpdateAccountResponse updateAccountResponse = accountService.update(account);
        return success(updateAccountResponse);
    }

    @DeleteMapping("/{id}")
    public UpdateAccountResponse delete(@PathVariable Integer id){
        return success(accountService.delete(id));
    }
}
