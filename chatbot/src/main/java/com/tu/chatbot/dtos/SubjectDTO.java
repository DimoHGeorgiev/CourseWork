package com.tu.chatbot.dtos;

import com.tu.chatbot.models.Subject;

public class SubjectDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SubjectDTO of(Subject subject) {
        SubjectDTO newSubjectDTO = new SubjectDTO();
        newSubjectDTO.setName(subject.getName());

        return newSubjectDTO;
    }
}
