package com.timetable.project.timetabler;

import com.timetable.project.dto.*;
import com.timetable.project.factory.DTOFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "/timetabler")
@Validated
public class TimeTablerController {
    private final TimetablerService timetablerService;
    private final DTOFactory dtoFactory;

    @PostMapping(path = "/checkCredentials")
    public TimetablerDTO checkCredentials(@Valid @RequestBody UserCredentialsDTO creds)
    {
        return dtoFactory.create(timetablerService.checkCredentials(creds));
    }

    @GetMapping(path = "/moduleCatalogue")
    public List<ModuleDTO> getModulesCatalogue()
    {
        return dtoFactory.create(timetablerService.getModuleCatalogue());
    }

    @PostMapping(path = "/scheduleClass")
    public ClassScheduleDTO scheduleClass(@Valid @RequestBody NewClassScheduleDTO scheduleData)
    {
        return dtoFactory.create(timetablerService.scheduleClass(scheduleData));
    }

    @PutMapping(path = "/update/{id}/classSchedule")
    public ClassScheduleDTO updateClassSchedule(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int bookingId,
            @Valid @RequestBody NewClassScheduleDTO scheduleData)
    {
        return dtoFactory.create(
                timetablerService.updateClassSchedule(bookingId, scheduleData));
    }

    @DeleteMapping(path = "/delete/{id}/classSchedule")
    public boolean deleteClassSchedule(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id must be greater than zero") int bookingId)
    {
        return timetablerService.deleteClassSchedule(bookingId);
    }

    @GetMapping(path = "/get/classSchedules")
    public ArrayList<ClassScheduleDTO> getClassSchedules()
    {
        return timetablerService
                .getClassSchedules()
                .stream()
                .map(e -> dtoFactory.create(e))
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
