package ru.kuznetsov.MySpringBoot2Dbase.dao;

import org.springframework.stereotype.Repository;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

@Repository
public interface DisciplineDAO {
    List<Discipline> getAllDisciplines();

    Discipline getDisciplineById(int id);

    Discipline saveDiscipline(Discipline discipline);

    void deleteDisciplineById(int id);

    List<Discipline> getDisciplinesBySemester(int semester);

    List<Discipline> getDisciplinesByCredits(int credits);

    List<Discipline> getDisciplinesByNameContaining(String name);
}
