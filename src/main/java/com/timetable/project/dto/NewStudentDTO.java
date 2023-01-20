package com.timetable.project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class NewStudentDTO {

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @NotNull(message = "Email must have a value")
    @Email(message = "Email is not in the correct format")
    private final String email;

    @NotBlank(message = "Password cannot be blank")
    private final String password;

    @NotBlank(message = "Department cannot be blank")
    private final String departmentName;
}


