package com.saiDeveloper.E_commerce2_Backend.exception;

import com.saiDeveloper.E_commerce2_Backend.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponse> userExceptionHandler(UserException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ExceptionResponse> productExceptionHandler(ProductException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ExceptionResponse> productExceptionHandler(CartException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartItemException.class)
    public ResponseEntity<ExceptionResponse> productExceptionHandler(CartItemException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ExceptionResponse> productExceptionHandler(OrderException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressException.class)
    public ResponseEntity<ExceptionResponse> productExceptionHandler(AddressException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


}
