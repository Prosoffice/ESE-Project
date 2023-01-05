package com.timetable.project.student;

import com.timetable.project.entity.Student;
import com.timetable.project.entity.StudentDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDepartmentRepository extends JpaRepository<StudentDepartment, Integer> {
    StudentDepartment findByName(String name);

}
