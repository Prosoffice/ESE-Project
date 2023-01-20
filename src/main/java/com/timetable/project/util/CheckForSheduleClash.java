package com.timetable.project.util;

import com.timetable.project.dto.NewClassScheduleDTO;
import com.timetable.project.entity.ClassSchedule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CheckForSheduleClash{

    public boolean verify(NewClassScheduleDTO classScheduleInReview, List<ClassSchedule> allSchedules)
    {
        boolean result = false;

        for (ClassSchedule classSchedule : allSchedules) {
            if (classSchedule.getWeek() == classScheduleInReview.getWeek() &&
                    Objects.equals(classSchedule.getModule().getName(), classScheduleInReview.getModule())) {
                result = true;
                break;
            }
        }
        ;

        return result;
    }
}
