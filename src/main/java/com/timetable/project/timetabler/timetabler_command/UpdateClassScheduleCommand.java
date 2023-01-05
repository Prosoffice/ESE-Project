package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.dto.NewClassScheduleDTO;
import com.timetable.project.entity.ClassSchedule;
import com.timetable.project.factory.EntityFactory;
import com.timetable.project.timetabler.ClassScheduleRepository;
import com.timetable.project.timetabler.LectureRoomRepository;
import com.timetable.project.timetabler.ModuleRepository;
import com.timetable.project.util.DateParser;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateClassScheduleCommand implements Command{
    private final ClassScheduleRepository classScheduleRepository;
    private final int bookingId;
    private final NewClassScheduleDTO classScheduleUpdatedData;
    private final EntityFactory entityFactory;
    private final ModuleRepository moduleRepository;
    private final LectureRoomRepository lectureRoomRepository;
    private final DateParser dateParser;

    @Override
    public ClassSchedule execute()
    {
        Optional<ClassSchedule> optionalClassSchedule = classScheduleRepository.findById(bookingId);

        if (!optionalClassSchedule.isPresent())
        {
            return null;
        }

        ClassSchedule originalClassSchedule = optionalClassSchedule.get();

        ClassSchedule updatedClassSchedule = entityFactory.create(classScheduleUpdatedData, moduleRepository,
                lectureRoomRepository, dateParser);

        originalClassSchedule.setWeek(updatedClassSchedule.getWeek());
        originalClassSchedule.setModule(updatedClassSchedule.getModule());
        originalClassSchedule.setStart_time(updatedClassSchedule.getStart_time());
        originalClassSchedule.setEnd_time(updatedClassSchedule.getEnd_time());
        originalClassSchedule.setLecture_room(updatedClassSchedule.getLecture_room());

        return classScheduleRepository.save(originalClassSchedule);
    }
}
