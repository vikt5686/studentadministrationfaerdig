package com.example.studentadministration.controllers;

import com.example.studentadministration.model.Student;
import com.example.studentadministration.repositories.IStudentRepository;
import com.example.studentadministration.repositories.InMemoryStudentRepositoryImpl;
import com.example.studentadministration.repositories.StudentRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

@Controller
public class StudentController {

    private IStudentRepository studentRepository;

    public StudentController() {
        studentRepository = new StudentRepositoryImpl();
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students", studentRepository.readAll());
        return "index";
    }


    @GetMapping("/create")
    public String studentForm(Model model){
        Student newStudent = new Student();

        model.addAttribute("studentadd", newStudent);

        return "create";
    }



    @PostMapping("/create")
    public String createdStudent (@ModelAttribute Student student) {
        System.out.println(student.getEnrollmentDate());
    studentRepository.create(student);




        return "redirect:/";
    }



    @GetMapping("/edit")
    public String updateStudent(Model model, int id){
        model.addAttribute("studentupdate", studentRepository.read(id));

        return "edit";

        // Student stu  = studentRepository.update();
        // Få fat i en student. Bruge data, som  repo. Sende data videre til vores template (edit).
    }

    @PostMapping("/edit")
    public String studentUpdated(Student student, int id) {
        studentRepository.update(student);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteStudent(Student student, Model model, int id) {

        model.addAttribute("students", studentRepository.delete(id));

        return "redirect:/";
    }



    //Very simple prototype of GET-request with parameter
    //https://www.baeldung.com/spring-request-param
    //TODO Direct to detailed view of student


    //@ResponseBody
    @GetMapping("/detail")
    public String getStudentByParameter(Model model, int id) {

        //Student stu = studentRepository.read(id);

       model.addAttribute("students", studentRepository.read(id));

        return "detail";
    }


}
