package com.timetable.project.timetabler;

import com.timetable.project.entity.LectureRoom;
import com.timetable.project.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRoomRepository extends JpaRepository<LectureRoom, Integer> {
    LectureRoom findByName(String name);
}
