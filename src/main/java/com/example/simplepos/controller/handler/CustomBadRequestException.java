package com.example.simplepos.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)  // 400 Bad Request
public class CustomBadRequestException extends RuntimeException {
    private HttpStatus status;

    public CustomBadRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    // Getter for the status
    public HttpStatus getStatus() {
        return status;
    }
}
