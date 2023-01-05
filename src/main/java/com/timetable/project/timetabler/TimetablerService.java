package com.timetable.project.timetabler;

import com.timetable.project.dto.NewClassScheduleDTO;
import com.timetable.project.entity.ClassSchedule;
import com.timetable.project.entity.Module;
import com.timetable.project.entity.Timetabler;
import com.timetable.project.timetabler.timetabler_command.Command;
import com.timetable.project.timetabler.timetabler_command.TimetablerCommandFactory;
import com.timetable.project.dto.UserCredentialsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimetablerService {

    private final TimetablerCommandFactory timetablerCommandFactory;

    public Timetabler checkCredentials(UserCredentialsDTO creds) {
        {
            return
                    (Timetabler) timetablerCommandFactory
                            .create(Command.CHECK_CREDENTIALS, creds)
                            .execute();
        }
    }

    public Timetabler checkCredentials(String token)
    {
        return
                (Timetabler) timetablerCommandFactory
                        .create(Command.CHECK_TOKEN, token)
                        .execute();
    }

    public List<Module> getModuleCatalogue()
    {
        return
                (List<Module>)timetablerCommandFactory
                        .create(Command.GET_MODULE_CATALOGUE)
                        .execute();
    }

    public ClassSchedule scheduleClass(NewClassScheduleDTO scheduleClassData)
    {
        return (ClassSchedule) timetablerCommandFactory
                .create(Command.SCHEDULE_CLASS, scheduleClassData)
                .execute();
    }

    public ClassSchedule updateClassSchedule(int bookingId, NewClassScheduleDTO classScheduleUpdatedData)
    {
        return (ClassSchedule) timetablerCommandFactory
                .create(Command.UPDATE_CLASS_SCHEDULE, bookingId, classScheduleUpdatedData)
                .execute();
    }

    public boolean deleteClassSchedule(int bookingId)
    {
        return
                (boolean)timetablerCommandFactory
                        .create(Command.DELETE_CLASS_SCHEDULE, bookingId)
                        .execute();
    }

    public List<ClassSchedule> getClassSchedules()
    {
        return (List<ClassSchedule>)timetablerCommandFactory
                .create(Command.GET_CLASS_SCHEDULES)
                .execute();
    }

}
