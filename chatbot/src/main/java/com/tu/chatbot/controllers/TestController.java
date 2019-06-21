package com.tu.chatbot.controllers;

import com.tu.chatbot.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private EmailService emailService;

    @RequestMapping("/test")
    public String ping() {
        return "PING";
    }

    @RequestMapping("/mailCheck")
    public void testMail(){
        emailService.sendEmail("dimo.georgiev@sap.com", "TEST", "GRADE 6");
    }

    public EmailService getEmailService() {
        return emailService;
    }
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
