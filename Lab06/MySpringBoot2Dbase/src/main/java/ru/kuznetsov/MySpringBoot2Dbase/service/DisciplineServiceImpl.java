package ru.kuznetsov.MySpringBoot2Dbase.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.kuznetsov.MySpringBoot2Dbase.dao.DisciplineDAO;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

@Service
@Transactional
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineDAO disciplineDAO;

    public DisciplineServiceImpl(DisciplineDAO disciplineDAO) {
        this.disciplineDAO = disciplineDAO;
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        return disciplineDAO.getAllDisciplines();
    }

    @Override
    public Discipline saveDiscipline(Discipline student) {
        return disciplineDAO.saveDiscipline(student);
    }

    @Override
    public Discipline getDisciplineById(int id) {
        return disciplineDAO.getDisciplineById(id);
    }

    @Override
    public void deleteDisciplineById(int id) {
        disciplineDAO.deleteDisciplineById(id);
    }

    @Override
    public List<Discipline> getDisciplinesBySemester(int semester) {
        return disciplineDAO.getDisciplinesBySemester(semester);
    }

    @Override
    public List<Discipline> getDisciplinesByCredits(int credits) {
        return disciplineDAO.getDisciplinesByCredits(credits);
    }

    @Override
    public List<Discipline> getDisciplinesByNameContaining(String name) {
        return disciplineDAO.getDisciplinesByNameContaining(name);
    }
}
