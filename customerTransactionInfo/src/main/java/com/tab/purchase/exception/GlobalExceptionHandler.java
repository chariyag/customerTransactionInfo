package com.tab.purchase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransactionDateFormatError.class)
    public ResponseEntity<ErrorResponse> handlerTransactionDateFormatError(TransactionDateFormatError exception, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(exception.getCode(),exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerProductNotFoundException(ProductNotFoundException exception, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(exception.getCode(),exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
}
