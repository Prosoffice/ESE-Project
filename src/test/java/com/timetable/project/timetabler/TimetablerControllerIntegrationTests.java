package com.timetable.project.timetabler;

import com.timetable.project.entity.Timetabler;
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
public class TimetablerControllerIntegrationTests {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TimetablerRepository timetablerRepository;
    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    @Test
    public void when_TimetablerExistsAndCheckCredentialsWithCorrectValues_expect_StudentReturned() throws Exception
    {
        Timetabler timetabler = new Timetabler(
                0,
                "Bob Jury",
                "bobj@gmail.com",
                "Pass1234",
                null
        );


        timetablerRepository.save(timetabler);

        String json_credentialsToCheck =
                "{\"email\": \"bobj@gmail.com\"," +
                        "\"password\": \"Pass1234\"}";

        mockMvc
                .perform(post("/timetabler/checkCredentials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_credentialsToCheck))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Bob Jury"))
                .andExpect(jsonPath("$.email").value("bobj@gmail.com"))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    public void when_TimetablerExistsAndCheckCredentialsWithWrongCorrectValues_expect_NoTimetablerReturned() throws Exception
    {
        timetablerRepository.deleteAll();

        Timetabler timetabler = new Timetabler(
                0,
                "Frank Jake",
                "frankJ@gmail.com",
                "Pass1234",
                null
        );

        timetablerRepository.save(timetabler);

        String json_credentialsToCheck =
                "{\"email\": \"frankJ@gmail.com\"," +
                        "\"password\": \"WrongPassword\"}";

        mockMvc
                .perform(post("/timetabler/checkCredentials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_credentialsToCheck))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void fetchModuleCatalogueWithValidToken_expect_ModulesReturned() throws Exception
    {
        Timetabler timetabler = new Timetabler(
                0,
                "Frank Jake",
                "frankJ@gmail.com",
                "Pass1234",
                "123456"
        );

        timetablerRepository.deleteAll();

        timetablerRepository.save(timetabler);

        mockMvc
                .perform(get("/timetabler/moduleCatalogue").header("AUTHORIZATION", "123456"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void fetchModuleCatalogueWithInvalidToken_expect_NoTimetablerReturned() throws Exception
    {
        Timetabler timetabler = new Timetabler(
                0,
                "Frank Jake",
                "frankJ@gmail.com",
                "Pass1234",
                "123456"
        );

        timetablerRepository.deleteAll();

        timetablerRepository.save(timetabler);

        mockMvc
                .perform(get("/timetabler/moduleCatalogue").header("AUTHORIZATION", "654321"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void when_CreateClassScheduleWithValidData_expect_ClassScheduleDetails() throws Exception
    {
        Timetabler timetabler = new Timetabler(
                0,
                "Frank Jake",
                "frankJ@gmail.com",
                "Pass1234",
                "123456"
        );

        timetablerRepository.deleteAll();
        classScheduleRepository.deleteAll();

        timetablerRepository.save(timetabler);

        String json_classScheduleToCreate =
                "{\"lecture_room\": \"Cadman\"," +
                        "\"module\": \"Enterprise Systems\"," +
                        "\"start_time\": \"2022-02-28 19:00\"," +
                        "\"end_time\": \"2022-02-28 21:00\"," +
                        "\"week\": \"12\"}";

        mockMvc
                .perform(post("/timetabler/scheduleClass")
                        .header("AUTHORIZATION", "123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_classScheduleToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.week").value("12"))
                .andExpect(jsonPath("$.module").value("Enterprise Systems"))
                .andExpect(jsonPath("$.lecture_room").value("Cadman"))
                .andExpect(jsonPath("$.start_time").value("2022-02-28T19:00:00"))
                .andExpect(jsonPath("$.end_time").value("2022-02-28T21:00:00"));
    }

    @Test
    public void when_CreateClassScheduleWithClashingTime_expect_ClashingErrorMessage() throws Exception
    {
        Timetabler timetabler = new Timetabler(
                0,
                "Frank Jake",
                "frankJ@gmail.com",
                "Pass1234",
                "123456"
        );

        timetablerRepository.deleteAll();

        timetablerRepository.save(timetabler);

        String json_classScheduleToCreate =
                "{\"lecture_room\": \"Cadman\"," +
                        "\"module\": \"Enterprise Systems\"," +
                        "\"start_time\": \"2022-02-28 19:00\"," +
                        "\"end_time\": \"2022-02-28 21:00\"," +
                        "\"week\": \"12\"}";

        mockMvc
                .perform(post("/timetabler/scheduleClass")
                        .header("AUTHORIZATION", "123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_classScheduleToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").value("Class Schedule clashes with a previous shedule"));
    }

}
