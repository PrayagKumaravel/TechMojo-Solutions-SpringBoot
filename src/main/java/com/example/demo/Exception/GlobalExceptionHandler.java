package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.DTO.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = InputDoesntExist.class)
    public ResponseEntity<ResponseDto> InputDoesntExistHandler(InputDoesntExist ex){
        //responseentity is a sending type of error by http framework sping...<contenttype>

        return new ResponseEntity<>(ResponseDto.builder()
        .message(ex.getMessage())
        .status(HttpStatus.BAD_REQUEST.value()).build(),
            HttpStatus.BAD_REQUEST
        );
        
    }

    @ExceptionHandler(exception = InputAlreadyExist.class)
    public ResponseEntity<ResponseDto> InputAlreadyExistHandler(InputAlreadyExist ex){

        //handling exception and returning as ResponseDto isnide ResponseEntity<>, here responseEntity is proviede by http framework

        return new ResponseEntity<>(
            ResponseDto.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(ex.getMessage()).build()
            ,HttpStatus.BAD_REQUEST);
    }
}
