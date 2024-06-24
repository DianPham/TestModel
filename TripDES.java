import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.swing.*;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TripDES {
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec myKeySpec;
    private SecretKeyFactory mySecretKeyFactory;
    private Cipher inCipher;
    byte[] keyAsBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey secretKey;

    public String performEncryption(String plaintext, String key)
    {
        try {
            myEncryptionKey = key;
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            myKeySpec = new DESedeKeySpec(keyAsBytes);
            mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
            inCipher = Cipher.getInstance(myEncryptionScheme);
            secretKey = mySecretKeyFactory.generateSecret(myKeySpec);
            return encrypt(plaintext);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String performDecryption(String cipher, String key)
    {
        try {
            myEncryptionKey = key; // Lấy khóa từ textField txtkhoa.
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            myKeySpec = new DESedeKeySpec(keyAsBytes);
            mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
            inCipher = Cipher.getInstance(myEncryptionScheme);
            secretKey = mySecretKeyFactory.generateSecret(myKeySpec);
            return decrypt(cipher);// Hiển thị văn bản đã giải mã trên textField txtvanban.
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra trong quá trình giải mã!");
        }
        return null;
    }

    private String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            inCipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
            byte[] plainText = inCipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
    private String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            inCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = inCipher.doFinal(plainText);
            encryptedString = Base64.getEncoder().encodeToString(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public static void main(String[] args) {
        TripDES td = new TripDES();
        String cipher = td.performEncryption("mon thuc hanh bao, mat thong tin", "baomatthongtinbuivanthieu");
        String plain = td.performDecryption(cipher, "baomatthongtinbuivanthieu");
        System.out.println(cipher);
        System.out.println(plain);
    }
}
