package ru.kuznetsov.MySpringBoot2Dbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Student;
import ru.kuznetsov.MySpringBoot2Dbase.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> allStudents() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Student> students = studentService.getAllStudents();
            response.put("success", true);
            response.put("data", students);
            response.put("message", "Students retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to retrieve students");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudent(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                response.put("success", true);
                response.put("data", student);
                response.put("message", "Student found successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Student not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to retrieve student");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveStudent(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();
        try {
            Student savedStudent = studentService.saveStudent(student);
            response.put("success", true);
            response.put("data", savedStudent);
            response.put("message", "Student created successfully");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to create student");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();
        try {
            student.setId(id);
            Student updatedStudent = studentService.saveStudent(student);
            response.put("success", true);
            response.put("data", updatedStudent);
            response.put("message", "Student updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to update student");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            studentService.deleteStudentById(id);
            response.put("success", true);
            response.put("message", "Student deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to delete student");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
