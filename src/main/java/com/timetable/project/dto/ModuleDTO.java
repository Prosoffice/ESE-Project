package com.timetable.project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.*;

@RequiredArgsConstructor
@Getter
public class ModuleDTO {
    @Min(value = 1, message = "Id must be greater than zero")
    private final int id;

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @NotBlank(message = "Department name cannot be blank")
    private final String departmentName;
}