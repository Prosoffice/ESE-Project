package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.entity.Timetabler;
import com.timetable.project.timetabler.TimetablerRepository;
import com.timetable.project.dto.UserCredentialsDTO;
import com.timetable.project.util.StringHasher;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CheckCredentialsCommand implements Command {
    private final TimetablerRepository timetablerRepository;
    private final UserCredentialsDTO credentials;
    private final StringHasher stringHasher;

    @Override
    public Timetabler execute()
    {
        Timetabler timetabler = timetablerRepository.findByEmail(credentials.getEmail());

        if (timetabler != null && timetabler.getPassword().equals(credentials.getPassword()))
        {
            String token =
                    stringHasher.hashString(
                            timetabler.getEmail() + ":" + LocalDate.now().toString());
            timetabler.setToken(token);
            timetabler = timetablerRepository.save(timetabler);
            return timetabler;
        }

        return null;
    }
}
