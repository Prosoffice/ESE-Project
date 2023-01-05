package com.timetable.project.student.student_command;

import com.timetable.project.entity.Student;
import com.timetable.project.student.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetStudentCommand implements Command
{
    private final StudentRepository studentRepository;
    private final int id;

    @Override
    public Student execute()
    {
        return studentRepository.findById(id).orElse(null);
    }
}
