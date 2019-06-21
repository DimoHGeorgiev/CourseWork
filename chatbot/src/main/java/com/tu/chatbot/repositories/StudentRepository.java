package com.tu.chatbot.repositories;

import com.tu.chatbot.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByFacultyNumber(String facultyNumber);
}
