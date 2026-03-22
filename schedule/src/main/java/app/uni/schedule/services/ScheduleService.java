package app.uni.schedule.services;

import app.uni.schedule.dto.DayScheduleDto;
import app.uni.schedule.dto.ClassDto;
import app.uni.schedule.entities.*;
import app.uni.schedule.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<DayScheduleDto> getWeekScheduleForGroup(LocalDate start, LocalDate end, Group group) {
        return getWeekSchedule(start, end, group, null, null);
    }

    public List<DayScheduleDto> getWeekScheduleForProfessor(LocalDate start, LocalDate end, Professor professor) {
        return getWeekSchedule(start, end, null, professor, null);
    }

    public List<DayScheduleDto> getWeekScheduleForClassroom(LocalDate start, LocalDate end, Classroom classroom) {
        return getWeekSchedule(start, end, null, null, classroom);
    }

    private List<DayScheduleDto> getWeekSchedule(LocalDate start, LocalDate end, Group group, Professor professor, Classroom classroom) {
        List<DayScheduleDto> result = new ArrayList<>();
        LocalDate current = start;
        while (!current.isAfter(end)) {
            DayScheduleDto dayDto = new DayScheduleDto();
            dayDto.setDate(current);
            dayDto.setDayName(getRussianDayOfWeek(current.getDayOfWeek()));

            List<Schedule> schedules;
            if (group != null) {
                schedules = scheduleRepository.findByGroupAndDateOrderByStartTime(group, current);
            } else if (professor != null) {
                schedules = scheduleRepository.findByProfessorAndDateOrderByStartTime(professor, current);
            } else if (classroom != null) {
                schedules = scheduleRepository.findByClassroomAndDateOrderByStartTime(classroom, current);
            } else {
                schedules = new ArrayList<>();
            }

            List<ClassDto> classDtos = schedules.stream().map(this::convertToClassDto).collect(Collectors.toList());
            dayDto.setClasses(classDtos);
            result.add(dayDto);
            current = current.plusDays(1);
        }
        return result;
    }

    private ClassDto convertToClassDto(Schedule schedule) {
        String classroom = schedule.getClassroom().getNumber() + " (" + schedule.getClassroom().getBuilding() + ")";
        StringBuilder professorBuilder = new StringBuilder(schedule.getProfessor().getFullName());
        professorBuilder.append(", ").append(schedule.getProfessor().getAcademicRank());
        if (schedule.getProfessor().getAcademicDegree() != null && !schedule.getProfessor().getAcademicDegree().isEmpty()) {
            professorBuilder.append(", ").append(schedule.getProfessor().getAcademicDegree());
        }

        String subgroup = null;
        if (schedule.getSubgroup() != null) {
            subgroup = schedule.getSubgroup() + " п/г";
        }

        return ClassDto.builder().startTime(schedule.getStartTime()).endTime(schedule.getEndTime()).disciplineName(schedule.getDiscipline().getName()).classType(schedule.getClassType().getTitle()).classroom(classroom).professor(professorBuilder.toString()).subgroup(subgroup).groupCode(schedule.getGroup().getCode()).build();
    }

    private String getRussianDayOfWeek(DayOfWeek dow) {
        return switch (dow) {
            case MONDAY -> "пн";
            case TUESDAY -> "вт";
            case WEDNESDAY -> "ср";
            case THURSDAY -> "чт";
            case FRIDAY -> "пт";
            case SATURDAY -> "сб";
            default -> "";
        };
    }
}