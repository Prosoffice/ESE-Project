package com.timetable.project.timetabler.timetabler_command;

public interface Command
{
    int CHECK_CREDENTIALS = 1;
    int CHECK_TOKEN = 2;
    int CLEAR_TOKEN = 3;
    int GET_TIMETABLER = 4;
    int GET_MODULE_CATALOGUE = 5;
    int SCHEDULE_CLASS = 6;
    int UPDATE_CLASS_SCHEDULE = 7;
    int DELETE_CLASS_SCHEDULE = 8;
    int GET_CLASS_SCHEDULES = 9;
    Object execute();
}
