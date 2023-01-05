package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.entity.Timetabler;
import com.timetable.project.timetabler.TimetablerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetTimetablerCommand implements Command
{
    private final TimetablerRepository timetablerRepository;
    private final int id;

    @Override
    public Timetabler execute()
    {
        return timetablerRepository.findById(id).orElse(null);
    }
}
