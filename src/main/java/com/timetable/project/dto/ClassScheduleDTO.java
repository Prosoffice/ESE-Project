package com.timetable.project.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class ClassScheduleDTO {
    private final int id;
    private final LocalDateTime start_time;
    private final LocalDateTime end_time;
    private final String module;
    private final String lecture_room;
    private final int week;
}
