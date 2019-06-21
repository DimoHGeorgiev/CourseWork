package com.tu.chatbot.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Mark {

    @EmbeddedId
    private MarkId markId;

    private double value;

    public Mark(){

    }

    public Mark(int subjectId, String studentFacultyNumber){
        this.markId = new MarkId(studentFacultyNumber, subjectId);
    }

    public Mark(MarkId markId, double value) {
        this.markId = markId;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return markId.equals(mark.markId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(markId);
    }

    public MarkId getMarkId() {
        return markId;
    }

    public void setMarkId(MarkId markId) {
        this.markId = markId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
