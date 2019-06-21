package com.tu.chatbot.services;

import com.tu.chatbot.dtos.SubjectDTO;
import com.tu.chatbot.forms.SubjectForm;
import com.tu.chatbot.models.Subject;
import com.tu.chatbot.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectDTO> getAllSubject(){
        return subjectRepository.findAll().stream().map(SubjectDTO::of).collect(Collectors.toList());
    }

    public Subject saveSubject(SubjectForm subjectForm){
        Subject foundSubject = subjectRepository.findByName(subjectForm.getName());
        if(isNull(foundSubject)){
            return subjectRepository.saveAndFlush(Subject.of(subjectForm));
        }else{
            return null;
        }
    }

}
