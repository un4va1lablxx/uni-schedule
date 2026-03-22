package app.uni.schedule.services;

import app.uni.schedule.entities.*;
import app.uni.schedule.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Group> getAllGroups() { return groupRepository.findAll(); }
    public Group getGroupById(Long id) { return groupRepository.findById(id).orElseThrow(); }
    @Transactional
    public Group saveGroup(Group group) { return groupRepository.save(group); }
    @Transactional
    public void deleteGroup(Long id) { groupRepository.deleteById(id); }

    public List<Professor> getAllProfessors() { return professorRepository.findAll(); }
    public Professor getProfessorById(Long id) { return professorRepository.findById(id).orElseThrow(); }
    @Transactional
    public Professor saveProfessor(Professor professor) { return professorRepository.save(professor); }
    @Transactional
    public void deleteProfessor(Long id) { professorRepository.deleteById(id); }

    public List<Discipline> getAllDisciplines() { return disciplineRepository.findAll(); }
    public Discipline getDisciplineById(Long id) { return disciplineRepository.findById(id).orElseThrow(); }
    @Transactional
    public Discipline saveDiscipline(Discipline discipline) { return disciplineRepository.save(discipline); }
    @Transactional
    public void deleteDiscipline(Long id) { disciplineRepository.deleteById(id); }

    public List<Classroom> getAllClassrooms() { return classroomRepository.findAll(); }
    public Classroom getClassroomById(Long id) { return classroomRepository.findById(id).orElseThrow(); }
    @Transactional
    public Classroom saveClassroom(Classroom classroom) { return classroomRepository.save(classroom); }
    @Transactional
    public void deleteClassroom(Long id) { classroomRepository.deleteById(id); }

    public List<Schedule> getAllSchedules() { return scheduleRepository.findAll(); }
    public Schedule getScheduleById(Long id) { return scheduleRepository.findById(id).orElseThrow(); }
    @Transactional
    public Schedule saveSchedule(Schedule schedule) { return scheduleRepository.save(schedule); }
    @Transactional
    public void deleteSchedule(Long id) { scheduleRepository.deleteById(id); }
}