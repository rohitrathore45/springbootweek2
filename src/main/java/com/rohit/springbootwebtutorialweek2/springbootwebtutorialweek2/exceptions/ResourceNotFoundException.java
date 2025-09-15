package com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
