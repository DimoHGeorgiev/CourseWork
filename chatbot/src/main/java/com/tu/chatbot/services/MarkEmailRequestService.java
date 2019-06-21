package com.tu.chatbot.services;

import com.tu.chatbot.exceptions.NoSuchStudentException;
import com.tu.chatbot.exceptions.NoSuchSubjectException;
import com.tu.chatbot.forms.MarkForm;
import com.tu.chatbot.models.MarkEmailRequest;
import com.tu.chatbot.models.MarkId;
import com.tu.chatbot.models.Student;
import com.tu.chatbot.models.Subject;
import com.tu.chatbot.repositories.MarkEmailRequestRepository;
import com.tu.chatbot.repositories.StudentRepository;
import com.tu.chatbot.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class MarkEmailRequestService {
    private MarkEmailRequestRepository markEmailRequestRepository;
    private StudentRepository studentRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public MarkEmailRequestService(MarkEmailRequestRepository markEmailRequestRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.markEmailRequestRepository = markEmailRequestRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public MarkEmailRequest saveMarkEmailRequest(MarkForm markForm) throws NoSuchSubjectException, NoSuchStudentException {
        Subject foundSubject = getFoundSubject(markForm.getSubjectName());
        Student foundStudent = getFoundStudent(markForm.getStudentFacultyNumber());

        MarkEmailRequest markEmailRequest = new MarkEmailRequest(foundSubject.getId(), foundStudent.getFacultyNumber());

        MarkId markId = new MarkId(foundStudent.getFacultyNumber(), foundSubject.getId());
        MarkEmailRequest foundMark = markEmailRequestRepository.findByMarkId(markId);
        if (isNull(foundMark)) {
            return markEmailRequestRepository.saveAndFlush(markEmailRequest);
        } else {
            return null;
        }
    }

    public Boolean doesMarkEmailRequestExists(MarkForm markForm) throws NoSuchSubjectException, NoSuchStudentException {
        Subject foundSubject = getFoundSubject(markForm.getSubjectName());
        Student foundStudent = getFoundStudent(markForm.getStudentFacultyNumber());

        MarkId markId = new MarkId(foundStudent.getFacultyNumber(), foundSubject.getId());
        MarkEmailRequest markEmailRequest = markEmailRequestRepository.findByMarkId(markId);
        return nonNull(markEmailRequest);
    }

    public void deleteMarkEmailRequest(MarkForm markForm) throws NoSuchSubjectException, NoSuchStudentException {
        Subject foundSubject = getFoundSubject(markForm.getSubjectName());
        Student foundStudent = getFoundStudent(markForm.getStudentFacultyNumber());

        MarkId markId = new MarkId(foundStudent.getFacultyNumber(), foundSubject.getId());
        MarkEmailRequest markEmailRequest = markEmailRequestRepository.findByMarkId(markId);

        if(nonNull(markEmailRequest)){
            markEmailRequestRepository.delete(markEmailRequest);
        }
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
