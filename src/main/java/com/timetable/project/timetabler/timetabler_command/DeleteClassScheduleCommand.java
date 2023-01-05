package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.timetabler.ClassScheduleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteClassScheduleCommand implements Command{
    private final ClassScheduleRepository classScheduleRepository;
    private final int bookingId;

    @Override
    public Boolean execute()
    {
        if (classScheduleRepository.existsById(bookingId))
        {
            try
            {
                classScheduleRepository.deleteById(bookingId);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }

}
