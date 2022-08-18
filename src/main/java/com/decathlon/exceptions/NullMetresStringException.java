package com.decathlon.exceptions;

public class NullMetresStringException extends NullPointerException{
    public NullMetresStringException() {
        super("null value received for metres String");
    }
}
