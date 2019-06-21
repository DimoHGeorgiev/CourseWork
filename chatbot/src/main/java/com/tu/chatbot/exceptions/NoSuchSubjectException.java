package com.tu.chatbot.exceptions;

public class NoSuchSubjectException extends Exception {
    public NoSuchSubjectException(String message) {
        super(message);
    }

    public NoSuchSubjectException(String message, Throwable throwable) {
        super(message, throwable);
    }
}