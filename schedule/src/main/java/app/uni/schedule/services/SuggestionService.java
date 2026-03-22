package app.uni.schedule.services;

import app.uni.schedule.dto.SuggestionDto;
import app.uni.schedule.entities.Classroom;
import app.uni.schedule.entities.Group;
import app.uni.schedule.entities.Professor;
import app.uni.schedule.repositories.ClassroomRepository;
import app.uni.schedule.repositories.GroupRepository;
import app.uni.schedule.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<SuggestionDto> getSuggestions(String query, int limit) {
        List<SuggestionDto> suggestions = new ArrayList<>();
        String searchTerm = query.trim();

        List<Group> groups = groupRepository.findByCodeContainingIgnoreCase(searchTerm);
        suggestions.addAll(groups.stream().limit(limit / 3 + 1).map(g -> new SuggestionDto("группа", g.getCode(), g.getCode())).toList());

        List<Professor> professors = professorRepository.findByFullNameContainingIgnoreCase(searchTerm);
        suggestions.addAll(professors.stream().limit(limit / 3 + 1).map(p -> new SuggestionDto("преподаватель", p.getFullName(), p.getFullName())).toList());

        List<Classroom> classrooms = classroomRepository.findByNumberContainingIgnoreCase(searchTerm);
        suggestions.addAll(classrooms.stream().limit(limit / 3 + 1).map(c -> new SuggestionDto("аудитория", c.getNumber() + " (" + c.getBuilding() + ")", c.getNumber())).toList());

        return suggestions.stream().limit(limit).collect(Collectors.toList());
    }
}