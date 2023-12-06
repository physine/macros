package org.example.encryption;

import org.example.exceptions.IllegalAESKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption implements IEncryption{

    private static final String ALGORITHM = "AES";
    private byte[] KEY;

    @Inject
    public Encryption(){
        String envVarValue = System.getenv("AES_ENCRYPTION_KEY");
        setKEY(envVarValue.getBytes());
    }

    @Override
    public String encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Override
    public String decrypt(String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    @Override
    public void setKEY(byte[] KEY) {
        if (KEY != null && KEY.length == 32) {
            this.KEY = KEY;
        } else {
            throw new IllegalAESKeyException(String.format("Illegal state of AES-256 key in env variable AES_ENCRYPTION_KEY. Should be not null and of length 32.  Actual value: %s", KEY == null ? "null" : KEY.length));
        }
    }
}
