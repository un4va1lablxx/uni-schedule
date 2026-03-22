package app.uni.schedule.repositories;

import app.uni.schedule.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByNumberContainingIgnoreCase(String number);
}
