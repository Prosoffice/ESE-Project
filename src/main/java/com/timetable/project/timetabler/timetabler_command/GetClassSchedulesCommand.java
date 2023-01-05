package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.entity.ClassSchedule;
import com.timetable.project.timetabler.ClassScheduleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetClassSchedulesCommand implements Command{

    private final ClassScheduleRepository classScheduleRepository;

    @Override
    public List<ClassSchedule> execute()
    {
        return classScheduleRepository.findAll();
    }
}
