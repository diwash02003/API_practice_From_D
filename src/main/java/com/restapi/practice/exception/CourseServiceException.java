package com.restapi.practice.exception;

public class CourseServiceException extends RuntimeException{
    public CourseServiceException(String message, Throwable cause) {
        super(message,cause);
    }
}
