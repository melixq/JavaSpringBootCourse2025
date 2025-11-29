package ru.kuznetsov.MySpringBoot2Dbase.service;

import org.springframework.stereotype.Service;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

@Service
public interface DisciplineService {
    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline student);

    Discipline getDisciplineById(int id);

    void deleteDisciplineById(int id);

    List<Discipline> getDisciplinesBySemester(int semester);

    List<Discipline> getDisciplinesByCredits(int credits);

    List<Discipline> getDisciplinesByNameContaining(String name);
}
