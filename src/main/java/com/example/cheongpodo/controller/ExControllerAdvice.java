package com.example.cheongpodo.controller;

import com.example.cheongpodo.domain.AddressNotFoundException;
import com.example.cheongpodo.domain.CategoryNotFoundException;
import com.example.cheongpodo.domain.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AddressNotFoundException.class)
    public ErrorResult AddressNotFoundExHandle(AddressNotFoundException e) {
        return new ErrorResult("BAD", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult CategoryNotFoundExHandle(CategoryNotFoundException e) {
        return new ErrorResult("BAD", e.getMessage());
    }
}
