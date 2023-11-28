package org.example.encryption;

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

    @Test(expected = IllegalStateException.class)
    public void testEncryptionInvalidKeyNull() {
        Encryption encryption = new Encryption();
        encryption.setKEY(null);
    }
}