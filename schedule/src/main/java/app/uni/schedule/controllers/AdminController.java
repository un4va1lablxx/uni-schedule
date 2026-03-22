package app.uni.schedule.controllers;

import app.uni.schedule.entities.*;
import app.uni.schedule.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String adminPanel() {
        return "admin/index";
    }

    @GetMapping("/groups/add")
    public String addGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "admin/edit-group";
    }

    @PostMapping("/groups/add")
    public String addGroup(@ModelAttribute Group group) {
        adminService.saveGroup(group);
        return "redirect:/admin";
    }

    @GetMapping("/groups/edit/{id}")
    public String editGroupForm(@PathVariable Long id, Model model) {
        Group group = adminService.getGroupById(id);
        model.addAttribute("group", group);
        return "admin/edit-group";
    }

    @PostMapping("/groups/update/{id}")
    public String updateGroup(@PathVariable Long id, @ModelAttribute Group group) {
        group.setId(id);
        adminService.saveGroup(group);
        return "redirect:/admin";
    }

    @PostMapping("/groups/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        adminService.deleteGroup(id);
        return "redirect:/admin";
    }

    @GetMapping("/professors/add")
    public String addProfessorForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "admin/edit-professor";
    }

    @PostMapping("/professors/add")
    public String addProfessor(@ModelAttribute Professor professor) {
        adminService.saveProfessor(professor);
        return "redirect:/admin";
    }

    @GetMapping("/professors/edit/{id}")
    public String editProfessorForm(@PathVariable Long id, Model model) {
        Professor professor = adminService.getProfessorById(id);
        model.addAttribute("professor", professor);
        return "admin/edit-professor";
    }

    @PostMapping("/professors/update/{id}")
    public String updateProfessor(@PathVariable Long id, @ModelAttribute Professor professor) {
        professor.setId(id);
        adminService.saveProfessor(professor);
        return "redirect:/admin";
    }

    @PostMapping("/professors/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        adminService.deleteProfessor(id);
        return "redirect:/admin";
    }

    @GetMapping("/disciplines/add")
    public String addDisciplineForm(Model model) {
        model.addAttribute("discipline", new Discipline());
        return "admin/edit-discipline";
    }

    @PostMapping("/disciplines/add")
    public String addDiscipline(@ModelAttribute Discipline discipline) {
        adminService.saveDiscipline(discipline);
        return "redirect:/admin";
    }

    @GetMapping("/disciplines/edit/{id}")
    public String editDisciplineForm(@PathVariable Long id, Model model) {
        Discipline discipline = adminService.getDisciplineById(id);
        model.addAttribute("discipline", discipline);
        return "admin/edit-discipline";
    }

    @PostMapping("/disciplines/update/{id}")
    public String updateDiscipline(@PathVariable Long id, @ModelAttribute Discipline discipline) {
        discipline.setId(id);
        adminService.saveDiscipline(discipline);
        return "redirect:/admin";
    }

    @PostMapping("/disciplines/delete/{id}")
    public String deleteDiscipline(@PathVariable Long id) {
        adminService.deleteDiscipline(id);
        return "redirect:/admin";
    }

    @GetMapping("/classrooms/add")
    public String addClassroomForm(Model model) {
        model.addAttribute("classroom", new Classroom());
        return "admin/edit-classroom";
    }

    @PostMapping("/classrooms/add")
    public String addClassroom(@ModelAttribute Classroom classroom) {
        adminService.saveClassroom(classroom);
        return "redirect:/admin";
    }

    @GetMapping("/classrooms/edit/{id}")
    public String editClassroomForm(@PathVariable Long id, Model model) {
        Classroom classroom = adminService.getClassroomById(id);
        model.addAttribute("classroom", classroom);
        return "admin/edit-classroom";
    }

    @PostMapping("/classrooms/update/{id}")
    public String updateClassroom(@PathVariable Long id, @ModelAttribute Classroom classroom) {
        classroom.setId(id);
        adminService.saveClassroom(classroom);
        return "redirect:/admin";
    }

    @PostMapping("/classrooms/delete/{id}")
    public String deleteClassroom(@PathVariable Long id) {
        adminService.deleteClassroom(id);
        return "redirect:/admin";
    }

    @GetMapping("/schedule/add")
    public String addScheduleForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("groups", adminService.getAllGroups());
        model.addAttribute("professors", adminService.getAllProfessors());
        model.addAttribute("disciplines", adminService.getAllDisciplines());
        model.addAttribute("classrooms", adminService.getAllClassrooms());
        model.addAttribute("classTypes", ClassType.values());
        return "admin/edit-schedule";
    }

    @PostMapping("/schedule/add")
    public String addSchedule(@ModelAttribute Schedule schedule) {
        adminService.saveSchedule(schedule);
        return "redirect:/admin";
    }

    @GetMapping("/schedule/edit/{id}")
    public String editScheduleForm(@PathVariable Long id, Model model) {
        Schedule schedule = adminService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("groups", adminService.getAllGroups());
        model.addAttribute("professors", adminService.getAllProfessors());
        model.addAttribute("disciplines", adminService.getAllDisciplines());
        model.addAttribute("classrooms", adminService.getAllClassrooms());
        model.addAttribute("classTypes", ClassType.values());
        return "admin/edit-schedule";
    }

    @PostMapping("/schedule/update/{id}")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute Schedule schedule) {
        schedule.setId(id);
        adminService.saveSchedule(schedule);
        return "redirect:/admin";
    }

    @PostMapping("/schedule/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        adminService.deleteSchedule(id);
        return "redirect:/admin";
    }
}