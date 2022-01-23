package com.project.recruitment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "departments")
//public class Department extends BaseEntity {
public class Department {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "point")
    private Double point;
}
