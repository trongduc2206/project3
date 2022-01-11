package com.project.recruitment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "criteriasets")
public class CriteriaSet extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
