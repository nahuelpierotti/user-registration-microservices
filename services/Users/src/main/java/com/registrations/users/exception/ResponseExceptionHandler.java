package com.registrations.users.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    public List<CustomErrorRecord> errors= new ArrayList<>();

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<List<CustomErrorRecord>> handleUserAlreadyRegisteredException(
            UserAlreadyRegisteredException ex,
            WebRequest request)
    {
        CustomErrorRecord err = new CustomErrorRecord(Timestamp.valueOf(LocalDateTime.now()), 500, "A user is already registered with that email address. ");
            errors.add(err);
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<CustomErrorRecord>> handleAllExceptions(
            Exception ex,
            WebRequest request)
    {

        CustomErrorRecord err = new CustomErrorRecord(Timestamp.valueOf(LocalDateTime.now()), 400, ex.getMessage());
        errors.add(err);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField().concat(":").concat(err.getDefaultMessage()))
                .collect(Collectors.joining(","));

        CustomErrorRecord err = new CustomErrorRecord(Timestamp.valueOf(LocalDateTime.now()), 400, msg);
        errors.add(err);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
