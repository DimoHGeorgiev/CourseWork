package com.tu.chatbot.services;

import com.tu.chatbot.exceptions.NoSuchStudentException;
import com.tu.chatbot.exceptions.NoSuchSubjectException;
import com.tu.chatbot.forms.MarkForm;
import com.tu.chatbot.models.*;
import com.tu.chatbot.repositories.MarkRepository;
import com.tu.chatbot.repositories.StudentRepository;
import com.tu.chatbot.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class MarkService {
    private MarkRepository markRepository;
    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;
    private MarkEmailRequestService markEmailRequestService;
    private EmailService emailService;

    @Autowired
    public MarkService(MarkRepository markRepository, SubjectRepository subjectRepository, StudentRepository studentRepository, MarkEmailRequestService markEmailRequestService, EmailService emailService) {
        this.markRepository = markRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.markEmailRequestService = markEmailRequestService;
        this.emailService = emailService;
    }

    public Mark saveMark(MarkForm markForm) throws NoSuchSubjectException, NoSuchStudentException {
        Subject foundSubject = getFoundSubject(markForm.getSubjectName());
        Student foundStudent = getFoundStudent(markForm.getStudentFacultyNumber());

        notifyIfNecessary(markForm, foundSubject, foundStudent);

        Mark mark = new Mark(foundSubject.getId(), foundStudent.getFacultyNumber());
        mark.setValue(markForm.getValue());

        MarkId markId = new MarkId(foundStudent.getFacultyNumber(), foundSubject.getId());
        Mark foundMark = markRepository.findByMarkId(markId);
        if (isNull(foundMark)) {
            return markRepository.saveAndFlush(mark);
        } else {
            return null;
        }
    }

    private void notifyIfNecessary(MarkForm markForm, Subject subject, Student student) throws NoSuchSubjectException, NoSuchStudentException {
        if(markEmailRequestService.doesMarkEmailRequestExists(markForm)){
            emailService.notifyStudent(student.getEmail(), subject.getName(), markForm.getValue());
            markEmailRequestService.deleteMarkEmailRequest(markForm);
        }
    }

    public Double getMarkValue(MarkForm markForm) throws NoSuchSubjectException, NoSuchStudentException {
        Subject foundSubject = getFoundSubject(markForm.getSubjectName());
        Student foundStudent = getFoundStudent(markForm.getStudentFacultyNumber());

        MarkId markId = new MarkId(foundStudent.getFacultyNumber(), foundSubject.getId());
        Mark mark = markRepository.findByMarkId(markId);
        return mark.getValue();
    }

    public Subject getFoundSubject(String subjectName) throws NoSuchSubjectException {
        Subject foundSubject = subjectRepository.findByName(subjectName);
        if (isNull(foundSubject)) {
            throw new NoSuchSubjectException("NO SUCH SUBJECT WITH NAME" + subjectName);
        } else return foundSubject;
    }

    public Student getFoundStudent(String studentFacultyNumber) throws NoSuchStudentException {
        Student foundStudent = studentRepository.findByFacultyNumber(studentFacultyNumber);
        if (isNull(foundStudent)) {
            throw new NoSuchStudentException("NO SUCH STUDENT WITH FAC NUM: " + studentFacultyNumber);
        }else return foundStudent;
    }


}
