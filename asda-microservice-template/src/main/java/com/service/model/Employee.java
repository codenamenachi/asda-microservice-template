package com.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Employee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee extends BaseEntity {

    @Id
    @NotBlank
    @Column(name = "EmployeeId")
    private String empId;

    @NotBlank
    @Column(name = "Name")
    private String name;

    @NotBlank
    @Size(min = 3)
    @Column(name = "Designation")
    private String designation;

    @NotBlank
    @Column(name = "Salary")
    private double salary;
}
