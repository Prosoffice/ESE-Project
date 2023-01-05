package com.timetable.project.student.student_command;

import com.timetable.project.entity.ClassSchedule;
import com.timetable.project.entity.Module;
import com.timetable.project.entity.Student;
import com.timetable.project.student.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetTimetableCommand implements Command{

    private final StudentRepository studentRepository;
    private final int studentId;

    @Override
    public List<ClassSchedule> execute()
    {
        Student student = studentRepository.findById(studentId).orElse(null);
        List<Module> studentModules = student.getDepartment().getModules();

        List<ClassSchedule> classSchedules = new ArrayList<>();

        for (int counter = 0; counter < studentModules.size(); counter++) {
            List<ClassSchedule> moduleSchedules = studentModules.get(counter).getSchedule();
            if (moduleSchedules.size() >= 1){
                for (int secondCounter = 0; secondCounter < moduleSchedules.size(); secondCounter++){
                    classSchedules.add(moduleSchedules.get(secondCounter));
                }
            }
        };
        return classSchedules;
    }
}
