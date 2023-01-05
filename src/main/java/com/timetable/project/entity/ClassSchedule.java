package com.timetable.project.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "module", nullable = false)
    private Module module;

    @ManyToOne
    @JoinColumn(name = "lecture_room", nullable = false)
    private LectureRoom lecture_room;

    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private int week;

}
