package app.uni.schedule.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity

@Table(name = "classrooms", uniqueConstraints = @UniqueConstraint(columnNames = {"building", "number"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String building;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Schedule> schedules;
}
