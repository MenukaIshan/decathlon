package com.decathlon.exceptions;

public class NullTimeStringException extends NullPointerException {

    public NullTimeStringException() {
        super("Null String passed for minute:seconds string");
    }
}
