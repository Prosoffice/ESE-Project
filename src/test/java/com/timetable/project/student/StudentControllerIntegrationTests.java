package com.timetable.project.student;

import com.timetable.project.entity.Student;
import com.timetable.project.entity.StudentDepartment;
import com.timetable.project.timetabler.ModuleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentDepartmentRepository studentDepartmentRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    public void when_StudentExistsAndCheckCredentialsWithCorrectValues_expect_StudentReturned() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Pervasive Computing", null, null);
        Student student = new Student(
                0,
                "Frank Talker",
                "frankt@gmail.com",
                "Pass1234%",
                department,
                null
        );


        studentDepartmentRepository.save(department);
        student = studentRepository.save(student);

        String json_credentialsToCheck =
                        "{\"email\": \"frankt@gmail.com\"," +
                        "\"password\": \"Pass1234%\"}";

        mockMvc
                .perform(post("/student/checkCredentials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_credentialsToCheck))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Frank Talker"))
                .andExpect(jsonPath("$.email").value("frankt@gmail.com"))
                .andExpect(jsonPath("$.department").value("Pervasive Computing"))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    public void when_StudentExistsAndCreateStudent_expect_NoStudentReturned() throws Exception
    {

        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);
        Student student = new Student(
                0,
                "Henry Lower",
                "henry@gmail.com",
                "Password5",
                department,
                null);

        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();



        studentDepartmentRepository.save(department);
        student = studentRepository.save(student);

        String json_studentToCreate =
                        "{\"email\": \"henry@gmail.com\"," +
                        "\"name\": \"Henry Lower\"," +
                        "\"departmentName\": \"Cybersecurity\"," +
                        "\"password\": \"Password5\"}";

        mockMvc
                .perform(post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_studentToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void when_StudentDepartmentExistsStudentDoesNotExistAndCreateStudent_expect_StudentReturned() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);

        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();

        studentDepartmentRepository.save(department);


        String json_studentToCreate =
                        "{\"email\": \"henry@gmail.com\"," +
                        "\"name\": \"Henry Lower\"," +
                        "\"departmentName\": \"Cybersecurity\"," +
                        "\"password\": \"Password5\"}";

        mockMvc
                .perform(post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_studentToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Henry Lower"))
                .andExpect(jsonPath("$.department").value("Cybersecurity"))
                .andExpect(jsonPath("$.email").value("henry@gmail.com"))
                .andExpect(jsonPath("$.token").isEmpty());
    }

    @Test
    public void when_CreateStudentWithInvalidValues_expect_BadRequestAndCorrectErrorMessages() throws Exception
    {
        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();

        String json_studentToCreate =
                        "{\"email\": \"??\"," +
                        "\"name\": \"\"," +
                        "\"departmentName\": \"\"," +
                        "\"password\": \"\"}";

        mockMvc
                .perform(post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_studentToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Name cannot be blank"))
                .andExpect(jsonPath("$.departmentName").value("Department cannot be blank"))
                .andExpect(jsonPath("$.email").value("Email is not in the correct format"));
    }

    @Test
    public void when_CreateStudentWithNullEmail_expect_BadRequestAndCorrectErrorMessages() throws Exception
    {
        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();

        String json_studentToCreate =
                        "{\"name\": \"\"," +
                        "\"departmentName\": \"\"," +
                        "\"password\": \"\"}";

        mockMvc
                .perform(post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_studentToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Name cannot be blank"))
                .andExpect(jsonPath("$.departmentName").value("Department cannot be blank"))
                .andExpect(jsonPath("$.email").value("Email must have a value"));
    }

    @Test
    public void when_GetStudentWithInvalidToken_expect_UnauthorizedRequest() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);
        Student student = new Student(
                0,
                "Frank Lower",
                "frankj@gmail.com",
                "Password5",
                department,
                "893282");

        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();


        studentDepartmentRepository.save(department);
        student = studentRepository.save(student);

        mockMvc
                .perform(get("/student/get/"+ student.getId()).header("AUTHORIZATION", "8982"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void when_GetStudentWithValidToken_expect_StudentReturned() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);
        Student student = new Student(
                0,
                "Frank Lower",
                "frankj@gmail.com",
                "Password5",
                department,
                "893282");

        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();


        studentDepartmentRepository.save(department);
        student = studentRepository.save(student);

        mockMvc
                .perform(get("/student/get/"+ student.getId()).header("AUTHORIZATION", "893282"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Frank Lower"))
                .andExpect(jsonPath("$.email").value("frankj@gmail.com"))
                .andExpect(jsonPath("$.department").value("Cybersecurity"))
                .andExpect(jsonPath("$.token").value("893282"));
    }
    @Test
    public void when_GetStudentModulesWithInvalidToken_expect_UnauthorizedRequest() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);
        Student student = new Student(
                0,
                "Frank Lower",
                "frankj@gmail.com",
                "Password5",
                department,
                "893282");

        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();


        studentDepartmentRepository.save(department);
        student = studentRepository.save(student);

        mockMvc
                .perform(get("/student/modules/" + student.getId()).header("AUTHORIZATION", "8982"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void when_GetStudentModulesWithValidToken_expect_ModulesReturned() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);
        Student student = new Student(
                0,
                "Frank Lower",
                "frankj@gmail.com",
                "Password5",
                department,
                "893282");

        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();


        studentDepartmentRepository.save(department);
        student = studentRepository.save(student);

        mockMvc
                .perform(get("/student/modules/" + student.getId()).header("AUTHORIZATION", "893282"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void when_GetStudentTimeTableWithInvalidToken_expect_UnauthorizedRequest() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);
        Student student = new Student(
                0,
                "Frank Lower",
                "frankj@gmail.com",
                "Password5",
                department,
                "893282");

        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();


        studentDepartmentRepository.save(department);
        student = studentRepository.save(student);

        mockMvc
                .perform(get("/student/timetable/"+ student.getId()).header("AUTHORIZATION", "8982"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void when_GetStudentTimeTableWithvalidToken_expect_TimetableReturned() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);
        Student student = new Student(
                0,
                "Frank Lower",
                "frankj@gmail.com",
                "Password5",
                department,
                "893282");

        moduleRepository.deleteAll();
        studentRepository.deleteAll();
        studentDepartmentRepository.deleteAll();


        studentDepartmentRepository.save(department);
        student = studentRepository.save(student);

        mockMvc
                .perform(get("/student/timetable/"+ student.getId()).header("AUTHORIZATION", "893282"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }



}

