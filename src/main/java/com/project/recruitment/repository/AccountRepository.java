package com.project.recruitment.repository;

import com.project.recruitment.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsernameAndPassword(String username, String password);
    Optional<Account> findById(Integer id);
    void deleteById(Integer id);
}
