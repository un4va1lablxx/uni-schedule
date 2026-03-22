package app.uni.schedule.repositories;

import app.uni.schedule.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
