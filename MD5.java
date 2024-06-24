import java.security.MessageDigest;

public class MD5 {
    public String performEncryption (String plaintext)
    {
        return encrypt(plaintext);
    }

    private String encrypt(String plaintext) {
        try {

            // Initialize MessageDigest with MD5 algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plaintext.getBytes());

            // Convert hash bytes to hexadecimal format
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Digest (in hex format): " + sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args)
    {
        MD5 md5 = new MD5();
        String cipher = md5.performEncryption("phamdoanhoangdanhoangdan123");
        System.out.println("Cipher: " + cipher);
    }
}
