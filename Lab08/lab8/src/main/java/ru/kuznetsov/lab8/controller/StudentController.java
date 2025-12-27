package ru.kuznetsov.lab8.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kuznetsov.lab8.entity.Student;
import ru.kuznetsov.lab8.repository.StudentRepository;

import java.util.Optional;

@Slf4j
@Controller
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/list")
    public ModelAndView getAllStudents(){
        log.info("/list -> connection");
        return new ModelAndView("list-students")
                .addObject("students", studentRepository.findAll());
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm() {
        return new ModelAndView("add-student-form")
                .addObject("student", new Student());
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long studentId) {
        var mav = new ModelAndView("add-student-form");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        var student = new Student();
        if (optionalStudent.isPresent())
            student = optionalStudent.get();

        mav.addObject("student", student);
        return mav;
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentId) {
        studentRepository.deleteById(studentId);
        return "redirect:/list";
    }
}
