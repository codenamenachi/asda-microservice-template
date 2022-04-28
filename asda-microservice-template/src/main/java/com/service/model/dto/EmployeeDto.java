package com.service.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {

    @NotBlank
    private String empId;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 3)
    private String designation;

    @NotBlank
    private double salary;
}
