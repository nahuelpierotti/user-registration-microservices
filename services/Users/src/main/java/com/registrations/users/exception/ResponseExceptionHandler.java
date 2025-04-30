package com.registrations.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResponseExceptionHandler {

    public List<CustomErrorResponse> errors= new ArrayList<>();

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<List<CustomErrorResponse>> handleUserAlreadyRegisteredException(
            UserAlreadyRegisteredException ex,
            WebRequest request)
    {
            CustomErrorResponse err = new CustomErrorResponse(Timestamp.valueOf(LocalDateTime.now()), 500, "An user is already registered with that email address. "+request.getDescription(false));
            errors.add(err);
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
