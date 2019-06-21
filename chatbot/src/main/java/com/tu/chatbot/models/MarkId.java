package com.tu.chatbot.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MarkId implements Serializable {
    private String studentFacultyNumber;
    private int subjectId;

    public MarkId(){

    }

    public MarkId(String studentFacultyNumber, int subjectId) {
        this.studentFacultyNumber = studentFacultyNumber;
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkId markId = (MarkId) o;
        return subjectId == markId.subjectId &&
                Objects.equals(studentFacultyNumber, markId.studentFacultyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentFacultyNumber, subjectId);
    }

    public String getStudentId() {
        return studentFacultyNumber;
    }

    public void setStudentId(String studentId) {
        this.studentFacultyNumber = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
