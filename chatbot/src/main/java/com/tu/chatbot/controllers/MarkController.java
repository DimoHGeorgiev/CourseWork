package com.tu.chatbot.controllers;

import com.tu.chatbot.exceptions.NoSuchStudentException;
import com.tu.chatbot.exceptions.NoSuchSubjectException;
import com.tu.chatbot.forms.MarkForm;
import com.tu.chatbot.models.Mark;
import com.tu.chatbot.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("marks")
public class MarkController {

    private MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addMark(@Valid @RequestBody MarkForm markForm, BindingResult bindingResult) throws NoSuchSubjectException, NoSuchStudentException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad form request");
        }

        Mark savedMark = markService.saveMark(markForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMark);
    }

    @GetMapping(produces="application/json")
    public ResponseEntity<?> getMark(@RequestParam String studentFacultyNumber, @RequestParam String subjectName) throws NoSuchSubjectException, NoSuchStudentException {
        MarkForm markForm = new MarkForm();
        markForm.setStudentFacultyNumber(studentFacultyNumber);
        markForm.setSubjectName(subjectName);
        Double markValue = markService.getMarkValue(markForm);

        return ResponseEntity.status(HttpStatus.OK).body(markValue);
    }


}
