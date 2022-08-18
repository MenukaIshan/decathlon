package com.decathlon.exceptions;

import java.io.IOException;

public class FileWriteException extends IOException {
    public FileWriteException() {
        super("Output file writing error");
    }
}
