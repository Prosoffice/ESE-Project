package com.timetable.project.student.student_command;

public interface Command
{
    int CHECK_CREDENTIALS = 1;
    int CHECK_TOKEN = 2;
    int CLEAR_TOKEN = 3;
    int CREATE_STUDENT = 4;
    int GET_STUDENT = 5;
    int DELETE_STUDENT = 6;
    int GET_STUDENT_DEPARTMENT_DATA = 7;
    int GET_STUDENT_TIMETABLE = 8;

    Object execute();
}
