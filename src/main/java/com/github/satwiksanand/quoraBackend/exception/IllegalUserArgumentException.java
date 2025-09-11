package com.github.satwiksanand.quoraBackend.exception;

public class IllegalUserArgumentException extends RuntimeException{
    public IllegalUserArgumentException(String message){
        super(message);
    }
}
