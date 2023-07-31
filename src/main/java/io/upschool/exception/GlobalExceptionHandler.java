package io.upschool.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value  = { DuplicateEntryException.class})
    protected ResponseEntity<Object> handleDuplicate(DuplicateEntryException ex, WebRequest request) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", LocalDateTime.now());
        objectBody.put("Error", ex.getMessage());
        objectBody.put("Errors", ex.getCause());
        return ResponseEntity.badRequest().body(objectBody);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", LocalDateTime.now());
        objectBody.put("Status", status.value());

        Map<String, Object> errorBody = new LinkedHashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(x -> errorBody.put(x.getField(),x.getDefaultMessage()));

        objectBody.put("Errors", errorBody);

        return new ResponseEntity<>(objectBody, status);
    }

}
