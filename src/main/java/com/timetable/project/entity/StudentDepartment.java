package com.timetable.project.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class StudentDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "department")
    @OrderBy(value = "name")
    private List<Student> students;

    @OneToMany(mappedBy = "department")
    @OrderBy(value = "name")
    private List<Module> modules;

    @Override
    public String toString()
    {
        return name;
    }
}
