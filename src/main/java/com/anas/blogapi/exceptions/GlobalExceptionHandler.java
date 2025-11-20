package com.anas.blogapi.exceptions;

import com.anas.blogapi.payloads.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
       Map<String,String> resp = new HashMap<>();
         ex.getBindingResult().getAllErrors().forEach((error)-> {
             String fieldName = ((FieldError) error).getField();
             String message = error.getDefaultMessage();
             resp.put(fieldName, message);
         });
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(Exception ex){
        ApiResponse apiResponse = new ApiResponse("User not found with email: "+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(Exception ex){
        ApiResponse apiResponse = new ApiResponse("Authentication Failed: "+ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalExceptions(Exception ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse("An Unexpected Error occurred: "+message,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
