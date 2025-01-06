package com.example.cheongpodo.domain;

public class CategoryNotFoundException extends IllegalArgumentException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
