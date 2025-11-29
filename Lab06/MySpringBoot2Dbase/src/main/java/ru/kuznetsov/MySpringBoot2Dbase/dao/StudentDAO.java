package ru.kuznetsov.MySpringBoot2Dbase.dao;

import org.springframework.stereotype.Repository;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Student;

import java.util.List;

@Repository
public interface StudentDAO {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentById(int id);

    void deleteStudentById(int id);
}
