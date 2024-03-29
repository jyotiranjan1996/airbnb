package com.airbnb1.exception;

public class UsernameEmailAlreadyExistException extends RuntimeException{

    public UsernameEmailAlreadyExistException(String message)
    {
        super(message);
    }
}
