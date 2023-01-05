package com.timetable.project.timetabler;

import com.timetable.project.entity.Timetabler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimetablerRepository extends JpaRepository<Timetabler, Integer>{
    Timetabler findByEmail(String email);

    Timetabler findByToken(String token);
}
