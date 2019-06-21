package com.tu.chatbot.repositories;

import com.tu.chatbot.models.Mark;
import com.tu.chatbot.models.MarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, MarkId> {
    Mark findByMarkId(MarkId markId);

}
