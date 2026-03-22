package app.uni.schedule.dto;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClassDto {
    private LocalTime startTime;
    private LocalTime endTime;
    private String disciplineName;
    private String classType;
    private String classroom;
    private String professor;
    private String subgroup;
    private String groupCode;
}