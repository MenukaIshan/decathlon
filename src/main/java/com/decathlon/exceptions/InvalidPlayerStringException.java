package com.decathlon.exceptions;

public class InvalidPlayerStringException extends IllegalArgumentException{
    public InvalidPlayerStringException() {
        super("Illegal Player String received");
    }
}
