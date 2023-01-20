package com.timetable.project.factory;

import com.timetable.project.dto.*;
import com.timetable.project.entity.*;
import com.timetable.project.entity.Module;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOFactory {

    public StudentDTO create(Student student){

        if (student == null)
        {
            return null;
        }

        StudentDTO studentDTO =
                new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.getDepartment().toString(),
                        student.getToken());

        return studentDTO;

    }

    public StudentModuleDTO create(StudentDepartment department){

        if (department == null)
        {
            return null;
        }
        StudentModuleDTO studentModuleDTO = new StudentModuleDTO();

        studentModuleDTO.setModules(create(department.getModules()));

        return  studentModuleDTO;
    }

    public List<ModuleDTO> create(List<Module> modules)
    {
        return modules
                .stream()
                .map(m -> create(m))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ModuleDTO create(Module module)
    {
        if (module == null)
        {
            return null;
        }

        return
                new ModuleDTO(
                        module.getId(),
                        module.getName(),
                        module.getDepartment().getName()
                );
    }

    public TimetablerDTO create(Timetabler timetabler)
    {
        if (timetabler == null)
        {
            return null;
        }

        TimetablerDTO timetablerDTO =
                new TimetablerDTO(
                        timetabler.getId(),
                        timetabler.getName(),
                        timetabler.getEmail(),
                        timetabler.getToken());

        return timetablerDTO;
    }

    public ClassScheduleDTO create(ClassSchedule classSchedule) {
        if (classSchedule == null)
        {
            return null;
        }

        ClassScheduleDTO classScheduleDTO =
                new ClassScheduleDTO(
                        classSchedule.getId(),
                        classSchedule.getStart_time(),
                        classSchedule.getEnd_time(),
                        classSchedule.getModule().getName(),
                        classSchedule.getLecture_room().getName(),
                        classSchedule.getWeek()
                );

        return classScheduleDTO;
    }
}
