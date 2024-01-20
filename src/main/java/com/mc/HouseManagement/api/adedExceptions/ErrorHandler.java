package com.mc.HouseManagement.api.adedExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    /** Data not found exception called by user. **/
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(DataNotFoundException exc){
        UserErrorResponse err = new UserErrorResponse();
        err.setStat(HttpStatus.NOT_FOUND.value());
        err.setMessage(exc.getMessage());
        err.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    /** Bad input exception. **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        // Create a response containing validation error details
        UserErrorResponse err = new UserErrorResponse();
        err.setStat(HttpStatus.BAD_REQUEST.value());
        err.setMessage(errorMessage);
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    /** Universal return exception. **/
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception exc){
        System.out.println("Error " + exc);
        UserErrorResponse err = new UserErrorResponse();
        err.setStat(HttpStatus.BAD_REQUEST.value());
        err.setMessage(exc.getMessage());
        err.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
