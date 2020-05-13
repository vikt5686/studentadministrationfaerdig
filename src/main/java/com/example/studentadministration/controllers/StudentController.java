package com.example.studentadministration.controllers;

import com.example.studentadministration.model.Student;
import com.example.studentadministration.repositories.IStudentRepository;
import com.example.studentadministration.repositories.InMemoryStudentRepositoryImpl;
import com.example.studentadministration.repositories.StudentRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String create(Model model, Student student){



        return "create";
    }



    @PostMapping("/create")
    public String create (@ModelAttribute Student student) {


        return "redirect:/";
    }

    @GetMapping("/edit")
    public String updateStudent(Model model, int id){
        model.addAttribute("student", studentRepository.read(id));

        return "edit";

        // Student stu  = studentRepository.update();
        // Få fat i en student. Bruge data, som  repo. Sende data videre til vores template (edit).
    }



    //Very simple prototype of GET-request with parameter
    //https://www.baeldung.com/spring-request-param
    //TODO Direct to detailed view of student
    @GetMapping("/student")
    @ResponseBody
    public String getStudentByParameter(@RequestParam int id) {
        Student stu = studentRepository.read(id);

        return "The id is: " + stu.getFirstName() + "and the CPR-nr is " + stu.getCpr();
    }


}
