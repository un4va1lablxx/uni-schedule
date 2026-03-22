package app.uni.schedule.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DayScheduleDto {
    private String dayName;
    private LocalDate date;
    private List<ClassDto> classes;
}