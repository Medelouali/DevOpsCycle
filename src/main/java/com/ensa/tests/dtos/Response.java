package com.ensa.tests.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Response <T>{
    private boolean error;
    private String message;
    private T data;

    public Response(T data) {
        this.data = data;
        this.message="";
        this.error=false;
    }

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
        this.error=false;
    }
}
