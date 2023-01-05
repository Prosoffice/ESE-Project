package com.timetable.project.dto;

import com.timetable.project.dto.ModuleDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class StudentModuleDTO {
    @Setter
    private List<ModuleDTO> modules;
}

