package com.timetable.project.timetabler;

import com.timetable.project.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {
    Module findByName(String name);
}
