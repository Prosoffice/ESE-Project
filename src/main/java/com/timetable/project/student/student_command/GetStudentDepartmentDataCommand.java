package com.timetable.project.student.student_command;

import com.timetable.project.entity.Student;
import com.timetable.project.entity.StudentDepartment;
import com.timetable.project.student.StudentDepartmentRepository;
import com.timetable.project.student.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetStudentDepartmentDataCommand implements Command {
    private final StudentRepository studentRepository;
    private final StudentDepartmentRepository studentDepartmentRepository;
    private final int id;

    @Override
    public StudentDepartment execute()
    {
        Student student = studentRepository.findById(id).orElse(null);
        String studentDepartmentName = student.getDepartment().toString();
        StudentDepartment studentDepartment = studentDepartmentRepository.findByName(studentDepartmentName);

        return studentDepartment;
    }
}
