package com.timetable.project.student.student_command;

import com.timetable.project.entity.Student;
import com.timetable.project.student.StudentRepository;
import com.timetable.project.dto.UserCredentialsDTO;
import com.timetable.project.util.StringHasher;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CheckCredentialsCommand implements Command {
    private final StudentRepository studentRepository;
    private final UserCredentialsDTO credentials;
    private final StringHasher stringHasher;

    @Override
    public Student execute()
    {
        Student student = studentRepository.findByEmail(credentials.getEmail());

        if (student != null && student.getPassword().equals(credentials.getPassword()))
        {
            String token =
                    stringHasher.hashString(
                            student.getEmail() + ":" + LocalDate.now().toString());
            student.setToken(token);
            student = studentRepository.save(student);
            return student;
        }

        return null;
    }
}
