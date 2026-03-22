package app.uni.schedule.repositories;

import app.uni.schedule.entities.Classroom;
import app.uni.schedule.entities.Group;
import app.uni.schedule.entities.Professor;
import app.uni.schedule.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByGroupAndDateOrderByStartTime(Group group, LocalDate date);
    List<Schedule> findByProfessorAndDateOrderByStartTime(Professor professor, LocalDate date);
    List<Schedule> findByClassroomAndDateOrderByStartTime(Classroom classroom, LocalDate date);
    List<Schedule> findAllByOrderByDateDescStartTimeAsc();
    List<Schedule> findByGroupIdAndDateBetweenOrderByDateAscStartTimeAsc(Long groupId, LocalDate start, LocalDate end);
}
