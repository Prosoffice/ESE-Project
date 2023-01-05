package com.timetable.project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class TimetablerDTO {

    private final int id;
    private final String name;
    private final String email;
    private final String token;
}
