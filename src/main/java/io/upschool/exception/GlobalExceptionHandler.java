package io.upschool.exception;

import io.upschool.dto.response.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Database Error", ex.getMessage());
        objectBody.put("Error Cause", ex.getCause());

        var response = BaseResponse.builder()
                .isSuccess(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .errorBody(objectBody)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> notValidFields = new LinkedHashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(x -> notValidFields.put(x.getField(), x.getDefaultMessage()));


        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("Not Valid Field", notValidFields);

        var response = BaseResponse.<Object>builder()
                .isSuccess(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .errorBody(errorBody)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
