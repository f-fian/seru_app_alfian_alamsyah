package com.example.seru.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataAlreadyExistexeption extends RuntimeException{
    public DataAlreadyExistexeption(String message) {
        super(message);
    }
}
