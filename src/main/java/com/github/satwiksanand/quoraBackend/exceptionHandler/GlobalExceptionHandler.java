package com.github.satwiksanand.quoraBackend.exceptionHandler;

import com.github.satwiksanand.quoraBackend.dto.ErrorMessage;
import com.github.satwiksanand.quoraBackend.exception.IllegalUserArgumentException;
import com.github.satwiksanand.quoraBackend.exception.UserAlreadyExistsException;
import com.github.satwiksanand.quoraBackend.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        ErrorMessage err = ErrorMessage.builder()
                .message(ex.getMessage())
                .errorMessage("User already exists")
                .statusCode(HttpStatus.CONFLICT.value())
                .build();
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalUserArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalUserArgumentException(IllegalUserArgumentException ex){
        ErrorMessage err = ErrorMessage.builder()
                .message(ex.getMessage())
                .errorMessage("Invalid Input")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex){
        ErrorMessage err = ErrorMessage.builder()
                .message(ex.getMessage())
                .errorMessage("service unavailable")
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .build();
        return new ResponseEntity<>(err, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(EntityNotFoundException ex){
        ErrorMessage err = ErrorMessage.builder()
                .message(ex.getMessage())
                .errorMessage("Entity doesn't exist!")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
