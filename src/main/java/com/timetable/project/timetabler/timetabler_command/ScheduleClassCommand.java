package com.timetable.project.timetabler.timetabler_command;


import com.timetable.project.dto.NewClassScheduleDTO;
import com.timetable.project.entity.ClassSchedule;
import com.timetable.project.factory.EntityFactory;
import com.timetable.project.timetabler.ClassScheduleRepository;
import com.timetable.project.timetabler.LectureRoomRepository;
import com.timetable.project.timetabler.ModuleRepository;
import com.timetable.project.util.DateParser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleClassCommand implements Command{
    private final ClassScheduleRepository classScheduleRepository;
    private final NewClassScheduleDTO classScheduleData;
    private final EntityFactory entityFactory;
    private final ModuleRepository moduleRepository;
    private final LectureRoomRepository lectureRoomRepository;
    private final DateParser dateParser;

    @Override
    public ClassSchedule execute()
    {

        ClassSchedule possibleClassSchedule = entityFactory.create(classScheduleData, moduleRepository,
                lectureRoomRepository, dateParser);

        return classScheduleRepository.save(possibleClassSchedule);
    }
}
