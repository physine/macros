package org.example.exceptions;

public class IllegalAESKeyException extends IllegalStateException{
    public IllegalAESKeyException(String message) {
        super(message);
    }
}
