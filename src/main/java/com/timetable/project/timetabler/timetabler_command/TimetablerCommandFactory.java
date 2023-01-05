package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.dto.NewClassScheduleDTO;
import com.timetable.project.factory.EntityFactory;
import com.timetable.project.timetabler.ClassScheduleRepository;
import com.timetable.project.timetabler.LectureRoomRepository;
import com.timetable.project.timetabler.ModuleRepository;
import com.timetable.project.timetabler.TimetablerRepository;
import com.timetable.project.dto.UserCredentialsDTO;
import com.timetable.project.util.DateParser;
import com.timetable.project.util.StringHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.timetable.project.timetabler.timetabler_command.Command.*;

@Component
@RequiredArgsConstructor
public class TimetablerCommandFactory
{
    private final TimetablerRepository timetablerRepository;
    private final ModuleRepository moduleRepository;
    private final StringHasher stringHasher;
    private final ClassScheduleRepository classScheduleRepository;
    private final LectureRoomRepository lectureRoomRepository;
    private final DateParser dateParser;

    private final EntityFactory entityFactory;

    public Command create(int commandCode, Object... params)
    {
        switch (commandCode)
        {
            case CHECK_CREDENTIALS:
                return new CheckCredentialsCommand(timetablerRepository, (UserCredentialsDTO)params[0], stringHasher);
            case CHECK_TOKEN:
                return new CheckTokenCommand(timetablerRepository, (String)params[0]);
            case CLEAR_TOKEN:
                return new ClearTokenCommand(timetablerRepository, (Integer) params[0]);
            case GET_TIMETABLER:
                return new GetTimetablerCommand(timetablerRepository, (Integer)params[0]);
            case GET_MODULE_CATALOGUE:
                return new GetModuleCatalogueCommand(moduleRepository);
            case SCHEDULE_CLASS:
                return new ScheduleClassCommand(classScheduleRepository, (NewClassScheduleDTO)params[0], entityFactory,
                        moduleRepository, lectureRoomRepository, dateParser);
            case UPDATE_CLASS_SCHEDULE:
                return new UpdateClassScheduleCommand(classScheduleRepository, (Integer)params[0], (NewClassScheduleDTO)params[1],
                        entityFactory,moduleRepository, lectureRoomRepository, dateParser);
            case DELETE_CLASS_SCHEDULE:
                return new DeleteClassScheduleCommand(classScheduleRepository, (Integer)params[0]);
            case GET_CLASS_SCHEDULES:
                return new GetClassSchedulesCommand(classScheduleRepository);
            default:
                return null;
        }
    }
}
