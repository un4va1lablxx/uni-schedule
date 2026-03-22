package app.uni.schedule.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "professors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "academic_rank", nullable = false)
    private String academicRank;

    @Column(name = "academic_degree")
    private String academicDegree;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Schedule> schedules;
}