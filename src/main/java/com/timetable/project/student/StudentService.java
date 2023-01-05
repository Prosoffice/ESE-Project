package com.timetable.project.student;

import com.timetable.project.dto.NewStudentDTO;
import com.timetable.project.entity.ClassSchedule;
import com.timetable.project.entity.Student;
import com.timetable.project.entity.StudentDepartment;
import com.timetable.project.student.student_command.Command;
import com.timetable.project.student.student_command.CommandFactory;
import com.timetable.project.dto.UserCredentialsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class StudentService {

    private final CommandFactory commandFactory;

    private final StudentRepository studentRepository;

    public Student checkCredentials(UserCredentialsDTO creds)
    {
        return
                (Student) commandFactory
                        .create(Command.CHECK_CREDENTIALS, creds)
                        .execute();
    }

    public Student checkCredentials(String token)
    {
        return
                (Student) commandFactory
                        .create(Command.CHECK_TOKEN, token)
                        .execute();
    }

    public Student createStudent(NewStudentDTO student)
    {
        return
                (Student) commandFactory
                        .create(Command.CREATE_STUDENT, student)
                        .execute();
    }

    public boolean deleteStudent(int id)
    {
        return
                (boolean) commandFactory
                        .create(Command.DELETE_STUDENT, id)
                        .execute();
    }

    public Student getStudent(int id)
    {
        return
                (Student) commandFactory
                        .create(Command.GET_STUDENT, id)
                        .execute();
    }

    public StudentDepartment getModules(int id)
    {
        return (StudentDepartment) commandFactory
                .create(Command.GET_STUDENT_DEPARTMENT_DATA, id)
                .execute();
    }

    public List<ClassSchedule> getTimetable(int studentId)
    {
        return (List<ClassSchedule>) commandFactory
                .create(Command.GET_STUDENT_TIMETABLE, studentId)
                .execute();
    }


}
