package com.timetable.project.timetabler.timetabler_command;

import com.timetable.project.entity.Module;
import com.timetable.project.timetabler.ModuleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetModuleCatalogueCommand implements Command
{
    private final ModuleRepository moduleRepository;

    @Override
    public List<Module> execute()
    {
        return moduleRepository.findAll();
    }
}
