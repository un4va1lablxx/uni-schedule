package app.uni.schedule.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule",
        indexes = {
                @Index(name = "idx_schedule_date", columnList = "date"),
                @Index(name = "idx_schedule_group", columnList = "group_id"),
                @Index(name = "idx_schedule_professor", columnList = "professor_id")},
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_schedule_group_subgroup_time",
                        columnNames = {"date", "startTime", "group_id", "subgroup"}),
                @UniqueConstraint(name = "uk_schedule_professor_time",
                        columnNames = {"date", "startTime", "professor_id"}),
                @UniqueConstraint(name = "uk_schedule_classroom_time",
                        columnNames = {"date", "startTime", "classroom_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(nullable = true)
    private Integer subgroup;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_type", nullable = false)
    private ClassType classType;

    public String getClassTypeTitle() {
        return classType != null ? classType.getTitle() : "";
    }

}