package com.tu.chatbot.dtos;

import com.tu.chatbot.models.Student;

public class StudentDTO {

    private String facultyNumber;

    private String fName;

    private String lName;

    private String email;

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
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

    public static StudentDTO of(Student student) {
        StudentDTO newStudentDTO = new StudentDTO();
        newStudentDTO.setEmail(student.getEmail());
        newStudentDTO.setFacultyNumber(student.getFacultyNumber());
        newStudentDTO.setfName(student.getfName());
        newStudentDTO.setlName(student.getlName());

        return newStudentDTO;
    }
}
