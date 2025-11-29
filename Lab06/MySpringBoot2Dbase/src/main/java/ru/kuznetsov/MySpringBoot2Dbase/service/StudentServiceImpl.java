package ru.kuznetsov.MySpringBoot2Dbase.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.kuznetsov.MySpringBoot2Dbase.dao.StudentDAO;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Student;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentDAO.saveStudent(student);
    }

    @Override
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    public void deleteStudentById(int id) {
        studentDAO.deleteStudentById(id);
    }
}
