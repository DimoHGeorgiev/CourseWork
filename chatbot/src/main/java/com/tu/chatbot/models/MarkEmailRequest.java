package com.tu.chatbot.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity

public class MarkEmailRequest {
    @EmbeddedId
    private MarkId markId;

    public MarkEmailRequest(){

    }

    public MarkEmailRequest(int subjectId, String studentFacultyNumber){
        this.markId = new MarkId(studentFacultyNumber, subjectId);
    }

    public MarkId getMarkId() {
        return markId;
    }

    public void setMarkId(MarkId markId) {
        this.markId = markId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkEmailRequest that = (MarkEmailRequest) o;
        return Objects.equals(markId, that.markId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(markId);
    }
}
