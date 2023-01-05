package com.timetable.project.student.student_command;

import com.timetable.project.factory.EntityFactory;
import com.timetable.project.dto.NewStudentDTO;
import com.timetable.project.student.StudentDepartmentRepository;
import com.timetable.project.dto.UserCredentialsDTO;
import com.timetable.project.util.StringHasher;
import com.timetable.project.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.timetable.project.student.student_command.Command.*;

@Component
@RequiredArgsConstructor
public class CommandFactory
{
    private final StudentRepository studentRepository;
    private final EntityFactory entityFactory;
    private final StringHasher stringHasher;

    private  final StudentDepartmentRepository studentDepartmentRepository;

    public Command create(int commandCode, Object... params)
    {
        switch (commandCode)
        {
            case CHECK_CREDENTIALS:
                return new CheckCredentialsCommand(studentRepository, (UserCredentialsDTO)params[0], stringHasher);
            case CHECK_TOKEN:
                return new CheckTokenCommand(studentRepository, (String)params[0]);
            case CLEAR_TOKEN:
                return new ClearTokenCommand(studentRepository, (Integer) params[0]);
            case CREATE_STUDENT:
                return new CreateStudentCommand(studentDepartmentRepository, studentRepository, entityFactory, (NewStudentDTO) params[0]);
            case GET_STUDENT:
                return new GetStudentCommand(studentRepository, (Integer)params[0]);
            case DELETE_STUDENT:
                return new DeleteStudentCommand(studentRepository, (Integer)params[0]);
            case GET_STUDENT_DEPARTMENT_DATA:
                return new GetStudentDepartmentDataCommand(studentRepository, studentDepartmentRepository, (Integer)params[0]);
            case GET_STUDENT_TIMETABLE:
                return new GetTimetableCommand(studentRepository, (Integer)params[0]);
            default:
                return null;
        }
    }
}
