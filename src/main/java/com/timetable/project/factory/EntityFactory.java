package com.timetable.project.factory;
import com.timetable.project.dto.NewClassScheduleDTO;
import com.timetable.project.entity.ClassSchedule;
import com.timetable.project.entity.Student;
import com.timetable.project.entity.StudentDepartment;
import com.timetable.project.dto.NewStudentDTO;
import com.timetable.project.timetabler.LectureRoomRepository;
import com.timetable.project.timetabler.ModuleRepository;
import com.timetable.project.util.DateParser;
import org.springframework.stereotype.Component;

@Component
public class EntityFactory {

    public Student create(NewStudentDTO student, StudentDepartment department)
    {
        return new Student(
                0,
                student.getName(),
                student.getEmail(),
                student.getPassword(),
                department,
                null
        );
    }

    public ClassSchedule create(NewClassScheduleDTO classScheduleDataDTO, ModuleRepository moduleRepository,
                                LectureRoomRepository lectureRoomRepository, DateParser dateParser)
    {
        return new ClassSchedule(
                0,
                moduleRepository.findByName((classScheduleDataDTO.getModule())),
                lectureRoomRepository.findByName(classScheduleDataDTO.getLecture_room()),
                dateParser.convertStringToLocalDate(classScheduleDataDTO.getStart_time()),
                dateParser.convertStringToLocalDate(classScheduleDataDTO.getEnd_time()),
                classScheduleDataDTO.getWeek()
        );
    }
}
