package com.timetable.project.timetabler;

import com.timetable.project.entity.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Integer> {
}