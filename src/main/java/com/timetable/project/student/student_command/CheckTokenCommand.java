package com.timetable.project.student.student_command;

import com.timetable.project.entity.Student;
import com.timetable.project.student.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckTokenCommand implements Command
{
    private final StudentRepository studentRepository;
    private final String token;

    @Override
    public Student execute()
    {
        Student student = studentRepository.findByToken(token);
        if (student != null && student.getToken() != null)
        {
            return student;
        }

        return null;
    }
}
