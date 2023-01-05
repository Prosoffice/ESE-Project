package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.entity.Timetabler;
import com.timetable.project.timetabler.TimetablerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClearTokenCommand implements Command
{
    private final TimetablerRepository timetablerRepository;
    private final int id;

    @Override
    public Object execute()
    {
        Timetabler timetabler = timetablerRepository.findById(id).orElse(null);
        if (timetabler != null && timetabler.getToken() != null)
        {
            timetabler.setToken(null);
            timetablerRepository.save(timetabler);
        }

        return null;
    }
}
