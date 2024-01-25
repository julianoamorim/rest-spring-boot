package com.juliano.restapispringboot.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.juliano.restapispringboot.exception.ExceptionResponse;
import com.juliano.restapispringboot.exception.ResourceNotFindException;

//Tratamento de erro generico
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    //tratamento de erro generico
    @ExceptionHandler(Exception.class)
    public final ResponseEntity <ExceptionResponse> handleAllExceptions (
        Exception ex, WebRequest request){
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), ex.getMessage(), request.getDescription(false));
            
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Tratamento erro objeto nao encontrado
    @ExceptionHandler(ResourceNotFindException.class)
    public final ResponseEntity <ExceptionResponse> handleNotFoundExceptions (
        Exception ex, WebRequest request){
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), ex.getMessage(), request.getDescription(false));
            
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /* @ExceptionHandler(UnsupportedMathException.class)
    public final ResponseEntity <ExceptionResponse> handleNotFoundExceptions2 (
        Exception ex, WebRequest request){
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), ex.getMessage(), request.getDescription(false));
            
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    } */


}
