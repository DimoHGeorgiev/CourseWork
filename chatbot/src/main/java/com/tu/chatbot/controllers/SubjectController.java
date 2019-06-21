package com.tu.chatbot.controllers;

import com.tu.chatbot.dtos.StudentDTO;
import com.tu.chatbot.dtos.SubjectDTO;
import com.tu.chatbot.forms.StudentForm;
import com.tu.chatbot.forms.SubjectForm;
import com.tu.chatbot.models.Student;
import com.tu.chatbot.models.Subject;
import com.tu.chatbot.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("subjects")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getSubjects(){
        List<SubjectDTO> list = subjectService.getAllSubject();
        return ResponseEntity.ok(list);
    }

    @PostMapping(produces ="application/json")
    public ResponseEntity<?> addSubject(@Valid @RequestBody SubjectForm subjectForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad form request");
        }

        Subject savedSubject = subjectService.saveSubject(subjectForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubject);
    }
}
