package com.challenge.codingchallenge.exceptionhandler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

import static com.challenge.codingchallenge.constant.ResponseConstant.INTERNAL_SERVER_ERROR;
import static com.challenge.codingchallenge.constant.ResponseConstant.INVALID_DATE_FORMAT_PLEASE_SPECIFY_THE_DATE_AND_TIME_IN_THE_CORRECT_FORMAT_USING_ISO_8601_FORMAT;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException() {
        return ResponseEntity.badRequest().body(INVALID_DATE_FORMAT_PLEASE_SPECIFY_THE_DATE_AND_TIME_IN_THE_CORRECT_FORMAT_USING_ISO_8601_FORMAT);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException() {
        return ResponseEntity.internalServerError().body(INTERNAL_SERVER_ERROR);
    }

}
