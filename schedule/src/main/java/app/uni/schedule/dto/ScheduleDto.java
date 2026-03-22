package app.uni.schedule.dto;

import app.uni.schedule.entities.ClassType;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleDto {
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long groupId;
    private Long professorId;
    private Long disciplineId;
    private Long classroomId;
    private ClassType classType;
    private Integer subgroup;
}