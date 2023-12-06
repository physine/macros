package org.example.encryption;

import org.example.exceptions.IllegalAESKeyException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EncryptionTest {

    @Test
    public void testEncryptionDecryptionProcess() throws Exception {
        Encryption encryption = new Encryption();
        encryption.setKEY("de783bd6e924a76f0bf4962ab9e8ev25".getBytes());

        String planeText = "Test123";
        String cypherText = encryption.encrypt(planeText);
        assertEquals(planeText, encryption.decrypt(cypherText));

        planeText = "\"!#@$%^&*()_+-=[]{}|;:',.<>?/\\\"\"";
        cypherText = encryption.encrypt(planeText);
        assertEquals(planeText, encryption.decrypt(cypherText));

        planeText = "Test \\b123";
        cypherText = encryption.encrypt(planeText);
        assertEquals(planeText, encryption.decrypt(cypherText));

        planeText = "\"\"";
        cypherText = encryption.encrypt(planeText);
        assertEquals(planeText, encryption.decrypt(cypherText));

        planeText = "\"\"";
        cypherText = encryption.encrypt(planeText);
        assertEquals(planeText, encryption.decrypt(cypherText));

        planeText = "Test123";
        cypherText = encryption.encrypt(planeText);
        assertNotEquals("", encryption.decrypt(cypherText));
    }

    @Test
    public void testEncryptionValidKey() {
        Encryption encryption = new Encryption();
        encryption.setKEY("de783bd6e924a76df0bf462ab9e8ev25".getBytes());
    }

    @Test(expected = IllegalStateException.class)
    public void testEncryptionInvalidKeyToShort() {
        Encryption encryption = new Encryption();
        encryption.setKEY("de783bd6efev2".getBytes());
    }

    @Test(expected = IllegalAESKeyException.class)
    public void testEncryptionInvalidKeyNull() {
        Encryption encryption = new Encryption();
        encryption.setKEY(null);
    }

    @Test(expected = IllegalAESKeyException.class)
    public void testEncryptionInvalidKeyLength() {
        Encryption encryption = new Encryption();
        encryption.setKEY(hexStringToByteArray("33aebdb02626f9284c94c733f3bfc3")); // length 31
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

}