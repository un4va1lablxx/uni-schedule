package app.uni.schedule.repositories;

import app.uni.schedule.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByFullNameContainingIgnoreCase(String fullName);
}
