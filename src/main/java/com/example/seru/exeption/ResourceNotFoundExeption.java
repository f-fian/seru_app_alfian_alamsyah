package com.example.seru.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeption extends RuntimeException{
    public ResourceNotFoundExeption(String message) {
        super(message);
    }
}
