package com.timetable.project.student;

import com.timetable.project.dto.*;
import com.timetable.project.factory.DTOFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "/student")
@Validated
public class StudentController{

    private final StudentService studentService;
    private final DTOFactory dtoFactory;

    @PostMapping(path = "/checkCredentials")
    public StudentDTO checkCredentials(@Valid @RequestBody UserCredentialsDTO creds)
    {
        return dtoFactory.create(studentService.checkCredentials(creds));
    }

    @PostMapping(path = "/create")
    public StudentDTO createStudent(@Valid @RequestBody NewStudentDTO student)
    {
        return dtoFactory.create(studentService.createStudent(student));
    }

    @GetMapping(path = "/get/profile/{id}")
    public StudentDTO getStudent(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int id
    )
    {
        return dtoFactory.create(studentService.getStudent(id));
    }

    @GetMapping(path = "/modules/{id}")
    public StudentModuleDTO getStudentModules(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int id
    )
    {
        return dtoFactory.create(studentService.getModules(id));
    }

    @GetMapping(path = "/timetable/{studentId}")
    public ArrayList<ClassScheduleDTO> getTimetable(
            @PathVariable(name = "studentId")
            @Min(value = 1, message = "Id must be greater than zero") int studentId
    )
    {
        return studentService.
                getTimetable(studentId)
                .stream()
                .map(e -> dtoFactory.create(e))
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
