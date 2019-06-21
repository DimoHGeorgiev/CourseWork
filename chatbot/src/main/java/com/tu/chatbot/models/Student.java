package com.tu.chatbot.models;

import com.tu.chatbot.forms.StudentForm;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Student {
    @Id
    private String facultyNumber;

    private String fName;

    private String lName;

    private String email;

    public Student() {

    }

    public Student(String facultyNumber, String fName, String lName, String email) {
        this.facultyNumber = facultyNumber;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return facultyNumber.equals(student.facultyNumber) &&
                email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyNumber, email);
    }

    public static Student of(StudentForm studentForm){
        Student newStudent = new Student();
        newStudent.setEmail(studentForm.getEmail());
        newStudent.setFacultyNumber(studentForm.getFacultyNumber());
        newStudent.setfName(studentForm.getfName());
        newStudent.setlName(studentForm.getlName());

        return newStudent;
    }
}
