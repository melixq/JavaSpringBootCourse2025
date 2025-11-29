package ru.kuznetsov.MySpringBoot2Dbase.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Student;

import java.util.List;

@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {
    private final EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> getAllStudents() {
        Query q = entityManager.createQuery("from Student");
        List<Student> allStudents = q.getResultList();
        log.info("getAllStudents: " + allStudents);
        return allStudents;
    }

    @Override
    public Student saveStudent(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public Student getStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void deleteStudentById(int id) {
        Query q = entityManager.createQuery("delete from Student where id =:studentId");
        q.setParameter("studentId", id);
        q.executeUpdate();
    }
}
