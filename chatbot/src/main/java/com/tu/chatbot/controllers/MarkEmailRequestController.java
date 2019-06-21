package com.tu.chatbot.controllers;

import com.tu.chatbot.exceptions.NoSuchStudentException;
import com.tu.chatbot.exceptions.NoSuchSubjectException;
import com.tu.chatbot.forms.MarkForm;
import com.tu.chatbot.models.MarkEmailRequest;
import com.tu.chatbot.services.MarkEmailRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("markRequests")
public class MarkEmailRequestController {

    MarkEmailRequestService markEmailRequestService;

    @Autowired
    public MarkEmailRequestController(MarkEmailRequestService markEmailRequestService) {
        this.markEmailRequestService = markEmailRequestService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addMarkEmailRequest(@Valid @RequestBody MarkForm markForm, BindingResult bindingResult) throws NoSuchSubjectException, NoSuchStudentException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad form request");
        }

        MarkEmailRequest savedMarkEmailRequest = markEmailRequestService.saveMarkEmailRequest(markForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMarkEmailRequest);
    }
}
