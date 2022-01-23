package com.project.recruitment.service.impl;

import com.project.recruitment.controller.response.LoginResponse;
import com.project.recruitment.controller.response.ResultResponse;
import com.project.recruitment.controller.response.UpdateAccountResponse;
import com.project.recruitment.entity.Account;
import com.project.recruitment.repository.AccountRepository;
import com.project.recruitment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    @Override
    public LoginResponse findByUsernameAndPassword(String username, String password) {
        LoginResponse loginResponse = new LoginResponse();
        Optional<Account> accountsOptional = accountRepository.findByUsernameAndPassword(username, password);
        if(accountsOptional.isPresent()){
            loginResponse.setResult(true);
            loginResponse.setRole(accountsOptional.get().getRole());
        } else {
            loginResponse.setResult(false);
        }
        return loginResponse;
    }

    @Override
    public ResultResponse insert(Account account){
        ResultResponse resultResponse = new ResultResponse();
        String username = account.getUsername();
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if(accountOptional.isPresent()){
            resultResponse.setResult(false);
            resultResponse.setMessage("Username "+username+ " existed");
            return resultResponse;
        }
       account.setId(null);
       accountRepository.save(account);
       resultResponse.setResult(true);
       resultResponse.setMessage("Success inserted account with username "+username+" and password " + account.getPassword());
       return resultResponse;
    }

    @Override
    public UpdateAccountResponse update(Account account){
        Optional<Account> accountsOptional = accountRepository.findById(account.getId());
        UpdateAccountResponse updateAccountResponse = new UpdateAccountResponse();
        if(accountsOptional.isPresent()){
            Account newAccount = accountsOptional.get();
            newAccount.setUsername(account.getUsername());
            newAccount.setPassword(account.getPassword());
            newAccount.setRole(account.getRole());
            newAccount.setStatus(account.getStatus());
            accountRepository.save(newAccount);
            updateAccountResponse.setResult(true);
            updateAccountResponse.setId(account.getId());
        } else {
            updateAccountResponse.setResult(false);
        }
        return updateAccountResponse;
    }

    @Override
    public UpdateAccountResponse delete(Integer id){
        UpdateAccountResponse updateAccountResponse = new UpdateAccountResponse();
        Optional<Account> accountsOptional = accountRepository.findById(id);
        if(accountsOptional.isPresent()){
            accountRepository.deleteById(id);
            updateAccountResponse.setResult(true);
            updateAccountResponse.setId(id);
        } else {
            updateAccountResponse.setResult(false);
        }
        return updateAccountResponse;
    }
}
