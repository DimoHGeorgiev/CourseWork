package com.tu.chatbot.services;

import com.tu.chatbot.dtos.StudentDTO;
import com.tu.chatbot.forms.StudentForm;
import com.tu.chatbot.models.Student;
import com.tu.chatbot.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudent(){
        return studentRepository.findAll().stream().map(StudentDTO::of).collect(Collectors.toList());
    }

    public Student saveStudent(StudentForm studentForm){
        Student foundStudent = studentRepository.findByFacultyNumber(studentForm.getFacultyNumber());
        if(isNull(foundStudent)){
            return studentRepository.saveAndFlush(Student.of(studentForm));
        }else{
            return null;
        }
    }
}
