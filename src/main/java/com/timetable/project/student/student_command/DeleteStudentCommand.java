package com.timetable.project.student.student_command;

import com.timetable.project.student.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteStudentCommand implements Command
{
    private final StudentRepository studentRepository;
    private final int id;

    @Override
    public Boolean execute()
    {
        if (studentRepository.existsById(id))
        {
            try
            {
                studentRepository.deleteById(id);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }
}
