package ru.kuznetsov.MySpringBoot2Dbase.service;

import org.springframework.stereotype.Service;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Student;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentById(int id);

    void deleteStudentById(int id);
}
