package com.example.luggagekeeper.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException()
    {
        super("Passwords do not Match.");
    }
}
