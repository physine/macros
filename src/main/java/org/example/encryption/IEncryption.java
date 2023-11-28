package org.example.encryption;

public interface IEncryption {
    public String encrypt(String data) throws Exception;
    public String decrypt(String data) throws Exception;
    public void setKEY(byte[] KEY) throws IllegalStateException;
}
