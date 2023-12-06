package org.example.exceptions;

public class IllegalDecryptionInput extends RuntimeException{
    public IllegalDecryptionInput(String message) {
        super(message);
    }
}
