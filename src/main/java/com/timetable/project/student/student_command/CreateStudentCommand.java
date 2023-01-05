package com.timetable.project.student.student_command;

import com.timetable.project.entity.Student;
import com.timetable.project.entity.StudentDepartment;
import com.timetable.project.factory.EntityFactory;
import com.timetable.project.dto.NewStudentDTO;
import com.timetable.project.student.StudentDepartmentRepository;
import com.timetable.project.student.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateStudentCommand implements Command
{
    private final StudentDepartmentRepository studentDepartmentRepository;
    private final StudentRepository studentRepository;
    private final EntityFactory entityFactory;
    private final NewStudentDTO student;

    @Override
    public Student execute()
    {
        StudentDepartment department = studentDepartmentRepository.findByName(student.getDepartmentName());
        Student possibleNewStudent = entityFactory.create(student, department);

        if (emailAlreadyInUse(possibleNewStudent.getEmail()))
        {
            return null;
        }

        return studentRepository.save(possibleNewStudent);
    }

    private boolean emailAlreadyInUse(String email)
    {
        return studentRepository.findByEmail(email) != null;
    }
}
