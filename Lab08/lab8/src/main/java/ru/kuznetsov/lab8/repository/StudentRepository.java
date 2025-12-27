package ru.kuznetsov.lab8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuznetsov.lab8.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
