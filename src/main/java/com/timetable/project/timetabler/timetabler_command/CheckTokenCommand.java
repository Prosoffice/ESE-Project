package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.entity.Timetabler;
import com.timetable.project.timetabler.TimetablerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckTokenCommand implements Command
{
    private final TimetablerRepository timetablerRepository;
    private final String token;

    @Override
    public Timetabler execute()
    {
        Timetabler timetabler = timetablerRepository.findByToken(token);
        if (timetabler != null && timetabler.getToken() != null)
        {
            System.out.println("Present");
            return timetabler;
        }

        System.out.println("Absent");
        return null;
    }
}
