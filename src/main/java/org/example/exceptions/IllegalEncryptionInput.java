package org.example.exceptions;

public class IllegalEncryptionInput extends RuntimeException{
    public IllegalEncryptionInput(String message) {
        super(message);
    }
}
