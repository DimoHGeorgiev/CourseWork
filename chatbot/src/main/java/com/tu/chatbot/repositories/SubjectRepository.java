package com.tu.chatbot.repositories;

import com.tu.chatbot.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findByName(String name);
}
