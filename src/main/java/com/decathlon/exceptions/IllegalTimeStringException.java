package com.decathlon.exceptions;

public class IllegalTimeStringException extends IllegalArgumentException {
    public IllegalTimeStringException() {
        super("Illegal Minute:Second String passed");
    }
}
