package com.tu.chatbot.dtos;

import com.tu.chatbot.models.Mark;

public class MarkDTO {
    private String studentId;
    private int subjectId;
    private double value;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public static MarkDTO of(Mark mark){
        MarkDTO newMarkDTO = new MarkDTO();
        newMarkDTO.setStudentId(mark.getMarkId().getStudentId());
        newMarkDTO.setSubjectId(mark.getMarkId().getSubjectId());

        return newMarkDTO;
    }
}
