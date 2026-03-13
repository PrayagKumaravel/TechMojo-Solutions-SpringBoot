package com.example.demo.Exception;


public class InputDoesntExist extends RuntimeException{
    public InputDoesntExist(String message){
        super(message);
    }
}
