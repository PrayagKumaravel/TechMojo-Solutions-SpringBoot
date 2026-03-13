package com.example.demo.Exception;

public class InputAlreadyExist extends RuntimeException{
    public InputAlreadyExist(String message){
        super(message);
    }
}
