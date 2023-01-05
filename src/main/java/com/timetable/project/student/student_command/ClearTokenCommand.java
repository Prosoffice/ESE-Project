package com.timetable.project.student.student_command;

import com.timetable.project.entity.Student;
import com.timetable.project.student.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClearTokenCommand implements Command
{
    private final StudentRepository studentRepository;
    private final int id;

    @Override
    public Object execute()
    {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null && student.getToken() != null)
        {
            student.setToken(null);
            studentRepository.save(student);
        }

        return null;
    }
}
