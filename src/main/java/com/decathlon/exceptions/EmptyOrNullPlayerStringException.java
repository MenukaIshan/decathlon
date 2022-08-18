package com.decathlon.exceptions;

public class EmptyOrNullPlayerStringException extends NullPointerException{
    public EmptyOrNullPlayerStringException() {
        super("Empty or null Player String passed");
    }
}
