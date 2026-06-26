package org.airesume.aiservice.exception;

//import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;


@RestControllerAdvice
public class globalExceptionHandler{
    @ExceptionHandler(aiServiceException.class)
    public ResponseEntity<Map<String,Object>> handleAIServiceException(aiServiceException ex){
        Map<String,Object> error=new HashMap<>();
        //timestamp
        error.put("TimeStamp", LocalDateTime.now());
        //status
        error.put("status", HttpStatus.BAD_REQUEST.value());
        //error
        error.put("Error","AI Service Error");
        //message
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGenericException(Exception ex)
    {
        Map<String,Object> error=new HashMap<>();
        //timestamp
        error.put("TimeStamp",LocalDateTime.now());
        //status
        error.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        //error
        error.put("error","Internal Server Error");
        //message
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
