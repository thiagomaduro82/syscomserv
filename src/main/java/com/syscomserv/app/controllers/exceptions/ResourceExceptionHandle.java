package com.syscomserv.app.controllers.exceptions;

import com.syscomserv.app.services.exceptions.DataIntegrityViolationException;
import com.syscomserv.app.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;

@ControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationErrorFunction(MethodArgumentNotValidException e, ServletRequest request){
        ValidationError validationError = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Validation errors");
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()){
            validationError.setErrors(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
