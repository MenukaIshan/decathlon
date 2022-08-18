package com.decathlon.exceptions;

public class NullFilePathException extends NullPointerException{
    public NullFilePathException() {
        super("Null file path received for result file");
    }
}
