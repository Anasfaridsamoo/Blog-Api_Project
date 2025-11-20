package com.anas.blogapi.payloads;

import ch.qos.logback.core.status.Status;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private LocalDateTime timestamp;
    private String message;
    private HttpStatus status;

   public ApiResponse(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(String message,HttpStatus status){
        this();
        this.message = message;
        this.status = status;
    }
}
