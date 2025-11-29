package ru.kuznetsov.MySpringBoot2Dbase.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

@Slf4j
@Repository
public class DisciplineDAOImpl implements DisciplineDAO {
    private final EntityManager entityManager;

    public DisciplineDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        Query q = entityManager.createQuery("from Discipline");
        List<Discipline> allDisciplines = q.getResultList();
        log.info("getAllDisciplines: " + allDisciplines);
        return allDisciplines;
    }

    @Override
    public Discipline saveDiscipline(Discipline discipline) {
        return entityManager.merge(discipline);
    }

    @Override
    public Discipline getDisciplineById(int id) {
        return entityManager.find(Discipline.class, id);
    }

    @Override
    public void deleteDisciplineById(int id) {
        Query q = entityManager.createQuery("delete from Discipline where id =:disciplineId");
        q.setParameter("disciplineId", id);
        q.executeUpdate();
    }

    @Override
    public List<Discipline> getDisciplinesBySemester(int semester) {
        TypedQuery<Discipline> query = entityManager.createQuery(
                "SELECT d FROM Discipline d WHERE d.semester = :semester", Discipline.class);
        query.setParameter("semester", semester);
        List<Discipline> disciplines = query.getResultList();
        log.info("getDisciplinesBySemester: semester={}", semester);
        return disciplines;
    }

    @Override
    public List<Discipline> getDisciplinesByCredits(int credits) {
        TypedQuery<Discipline> query = entityManager.createQuery(
                "SELECT d FROM Discipline d WHERE d.credits = :credits", Discipline.class);
        query.setParameter("credits", credits);
        List<Discipline> disciplines = query.getResultList();
        log.info("getDisciplinesByCredits: credits={}", credits);
        return disciplines;
    }

    @Override
    public List<Discipline> getDisciplinesByNameContaining(String name) {
        TypedQuery<Discipline> query = entityManager.createQuery(
                "SELECT d FROM Discipline d WHERE d.name LIKE :name", Discipline.class);
        query.setParameter("name", "%" + name + "%");
        List<Discipline> disciplines = query.getResultList();
        log.info("getDisciplinesByNameContaining: name={}", name);
        return disciplines;
    }
}
