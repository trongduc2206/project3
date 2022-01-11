package com.project.recruitment.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Accounts")
public class Account extends BaseEntity {
    @Column(name = "role")
    private String role;

    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;

}
