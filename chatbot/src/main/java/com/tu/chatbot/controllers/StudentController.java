package com.tu.chatbot.controllers;

import com.tu.chatbot.dtos.StudentDTO;
import com.tu.chatbot.forms.StudentForm;
import com.tu.chatbot.models.Student;
import com.tu.chatbot.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getStudents(){
        List<StudentDTO> list = studentService.getAllStudent();
        return ResponseEntity.ok(list);
    }

    @PostMapping(produces ="application/json")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentForm studentForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad form request");
        }

        Student savedStudent = studentService.saveStudent(studentForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
}
