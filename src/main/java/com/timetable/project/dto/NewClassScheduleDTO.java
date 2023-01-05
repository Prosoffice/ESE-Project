package com.timetable.project.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class NewClassScheduleDTO {
    @NotBlank(message = "lecture_room cannot be blank")
    private final String lecture_room;

    @NotBlank(message = "module cannot be blank")
    private final String module;

    @NotBlank(message = "start_time cannot be blank")
    private final String start_time;

    @NotBlank(message = "end_time cannot be blank")
    private final String end_time;

    @NotNull(message = "week cannot be blank")
    private final int week;
}
