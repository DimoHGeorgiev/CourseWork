package com.tu.chatbot.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class SubjectForm {

    @NotEmpty
    @Length(max=50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
