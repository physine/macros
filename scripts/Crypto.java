import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;



public class Crypto {

    public static void main(String[] args) throws Exception {

        System.out.println("Loading env key");

        String envVarValue = System.getenv("AES_ENCRYPTION_KEY");
        System.out.println("Loading env key " + envVarValue);

        String text = encrypt(envVarValue, "foo");
        System.out.println("Encrypted text " + text);

        text = decrypt(envVarValue, text);
        System.out.println("decrypted text " + text);

    }
    public static String encrypt(String envVarValue, String data) throws Exception {
        Key key = new SecretKeySpec(envVarValue.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String envVarValue, String encryptedData) throws Exception {
        Key key = new SecretKeySpec(envVarValue.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}