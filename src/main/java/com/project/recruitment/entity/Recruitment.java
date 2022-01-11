package com.project.recruitment.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "recruitments")
//public class Recruitment extends BaseEntity {
public class Recruitment {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private int status;

    @Column(name = "started_time")
    private Date startedTime;

    @Column(name = "closed_time")
    private Date closedTime;

    @Column(name = "information")
    private String information;
}
