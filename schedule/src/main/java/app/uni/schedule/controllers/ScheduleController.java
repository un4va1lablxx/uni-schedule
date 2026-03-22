package app.uni.schedule.controllers;

import app.uni.schedule.dto.DayScheduleDto;
import app.uni.schedule.entities.*;
import app.uni.schedule.repositories.*;
import app.uni.schedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @GetMapping("/")
    public String home(@RequestParam(required = false) String query, @RequestParam(defaultValue = "0") int weekOffset, Model model) {
        if (query == null || query.trim().isEmpty()) {
            model.addAttribute("showWelcome", true);
            return "index";
        }

        LocalDate today = LocalDate.now();
        LocalDate targetDate = today.plusWeeks(weekOffset);
        LocalDate monday = targetDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate saturday = monday.plusDays(5);

        String searchTerm = query.trim();

        List<Group> groups = groupRepository.findByCodeContainingIgnoreCase(searchTerm);
        if (!groups.isEmpty()) {
            List<DayScheduleDto> weekDays = scheduleService.getWeekScheduleForGroup(monday, saturday, groups.get(0));
            model.addAttribute("weekDays", weekDays);
            model.addAttribute("weekStart", monday);
            model.addAttribute("weekEnd", saturday);
            model.addAttribute("weekOffset", weekOffset);
            model.addAttribute("query", searchTerm);
            return "index";
        }

        List<Professor> professors = professorRepository.findByFullNameContainingIgnoreCase(searchTerm);
        if (!professors.isEmpty()) {
            List<DayScheduleDto> weekDays = scheduleService.getWeekScheduleForProfessor(monday, saturday, professors.get(0));
            model.addAttribute("weekDays", weekDays);
            model.addAttribute("weekStart", monday);
            model.addAttribute("weekEnd", saturday);
            model.addAttribute("weekOffset", weekOffset);
            model.addAttribute("query", searchTerm);
            return "index";
        }

        List<Classroom> classrooms = classroomRepository.findByNumberContainingIgnoreCase(searchTerm);
        if (!classrooms.isEmpty()) {
            List<DayScheduleDto> weekDays = scheduleService.getWeekScheduleForClassroom(monday, saturday, classrooms.get(0));
            model.addAttribute("weekDays", weekDays);
            model.addAttribute("weekStart", monday);
            model.addAttribute("weekEnd", saturday);
            model.addAttribute("weekOffset", weekOffset);
            model.addAttribute("query", searchTerm);
            return "index";
        }
        model.addAttribute("error", "По вашему запросу ничего не найдено");
        return "index";
    }
}