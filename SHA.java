import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class SHA {

    public String performEncryption(String plaintext){
        return encrypt(plaintext);
    }
    private String encrypt (String plaintext) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plaintext.getBytes());

            // Perform the hash and get the resulting byte array
            byte[] byteData = md.digest();

            // Convert the byte array to hex format (Method 2)
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger("Error: " + ex);
        }
        return null;
    }

    public static void main(String[] args)
    {
        SHA sha = new SHA();
        String cipher = sha.performEncryption("phamdoanhoangdanhoangdan123");
        System.out.println("Cipher: " + cipher);
    }
}
