package com.example.simplepos.controller.handler;


import com.example.simplepos.entity.ModelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ModelResponse> handleBadRequest(CustomBadRequestException ex) {
        // Create a new ErrorResponse with the status, message, and current timestamp
        ModelResponse modelResponse = new ModelResponse(
                ex.getStatus().value(),  // HTTP status code
                ex.getMessage()                // Exception message
        );

        // Return the ErrorResponse as a ResponseEntity with a BAD_REQUEST status
        return new ResponseEntity<>(modelResponse, ex.getStatus());
    }
}