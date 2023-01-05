package com.timetable.project.student;


import com.timetable.project.entity.Module;
import com.timetable.project.entity.Student;
import com.timetable.project.entity.StudentDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

    @Test
    public void when_StudentExistsAndCheckCredentialsWithCorrectValues_expect_StudentReturned() throws Exception
    {
        StudentDepartment department = new StudentDepartment(0, "Cybersecurity", null, null);
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
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$.name").value("Frank Talker"))
//                .andExpect(jsonPath("$.email").value("frankt@gmail.com"));
    }


}

