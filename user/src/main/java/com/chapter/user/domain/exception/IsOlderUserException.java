package com.chapter.user.domain.exception;

public class IsOlderUserException extends RuntimeException {
    public IsOlderUserException(){
        super("The user must be older");
    }
}
