package app.uni.schedule.controllers;

import app.uni.schedule.entities.*;
import app.uni.schedule.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/groups")
    public List<Group> getGroups() { return groupRepository.findAll(); }
    @GetMapping("/professors")
    public List<Professor> getProfessors() { return professorRepository.findAll(); }
    @GetMapping("/disciplines")
    public List<Discipline> getDisciplines() { return disciplineRepository.findAll(); }
    @GetMapping("/classrooms")
    public List<Classroom> getClassrooms() { return classroomRepository.findAll(); }
    @GetMapping("/schedules")
    public List<Schedule> getSchedules() { return scheduleRepository.findAll(); }

    @DeleteMapping("/{entity}/{id}")
    public void deleteEntity(@PathVariable String entity, @PathVariable Long id) {
        switch (entity) {
            case "groups": groupRepository.deleteById(id); break;
            case "professors": professorRepository.deleteById(id); break;
            case "disciplines": disciplineRepository.deleteById(id); break;
            case "classrooms": classroomRepository.deleteById(id); break;
            case "schedule": scheduleRepository.deleteById(id); break;
            default: throw new IllegalArgumentException("Unknown entity: " + entity);
        }
    }
}