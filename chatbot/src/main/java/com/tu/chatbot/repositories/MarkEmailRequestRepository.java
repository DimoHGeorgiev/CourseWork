package com.tu.chatbot.repositories;

import com.tu.chatbot.models.MarkEmailRequest;
import com.tu.chatbot.models.MarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkEmailRequestRepository extends JpaRepository<MarkEmailRequest, MarkId> {
    MarkEmailRequest findByMarkId(MarkId markId);
}
