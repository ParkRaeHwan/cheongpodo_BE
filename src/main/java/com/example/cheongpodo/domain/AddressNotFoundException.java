package com.example.cheongpodo.domain;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String message) {
        super(message);
    }
}
