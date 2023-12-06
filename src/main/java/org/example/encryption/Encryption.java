package org.example.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.security.Key;
import java.util.Base64;

public class Encryption {

    private static final String ALGORITHM = "AES";
    private byte[] KEY;

    @Inject
    public Encryption() {
        String envVarValue = System.getenv("AES_ENCRYPTION_KEY");
        setKEY(envVarValue.getBytes());
    }

    public String encrypt(String data) throws Exception {
        Key key = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public void setKEY(byte[] KEY) {
        if (KEY != null && KEY.length == 32) {
            this.KEY = KEY;
        } else {
            throw new IllegalStateException();
        }
    }
}
