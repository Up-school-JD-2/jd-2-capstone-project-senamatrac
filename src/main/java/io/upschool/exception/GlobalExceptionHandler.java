package io.upschool.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DuplicateEntryException.class, DataNotFoundException.class, DataCannotDelete.class})
    protected ResponseEntity<Object> handleDuplicate(Exception ex, WebRequest request) {
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
                .forEach(x -> errorBody.put(x.getField(), x.getDefaultMessage()));
        objectBody.put("Errors", errorBody);

        return new ResponseEntity<>(objectBody, status);
    }

}
