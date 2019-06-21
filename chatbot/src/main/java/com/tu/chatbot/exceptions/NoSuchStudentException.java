package com.tu.chatbot.exceptions;

public class NoSuchStudentException extends Exception {
    public NoSuchStudentException(String message) {
        super(message);
    }

    public NoSuchStudentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}