public class Caesar {

    public String performEncryption(String plaintext, String key)
    {
        int k = Integer.valueOf(key.trim());
        return en_de_crypt(plaintext, k);
    }

    public String performDecryption(String cipher, String key)
    {
        int k = Integer.valueOf(key.trim());
        return en_de_crypt(cipher, -k);
    }

    private String en_de_crypt(String text, int key) {
        String result = "";
        int n = text.length();
        for (int i  = 0; i < n; i++)
        {
            result += encryptLetter(text.charAt(i), key);
        }
        return result;
        //To change body of generated methods, choose Tools | Templates.
    }
    private char encryptLetter(char c, int k) {
        if (!Character.isLetter(c)) return c;
        return (char)((((Character.toUpperCase(c) - 'A') + k) % 26 + 26) % 26 + 'A');
        //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        Caesar caesar = new Caesar();
        String cipher = caesar.performEncryption("hoangdan", "21");
        String plain = caesar.performDecryption(cipher, "21");
        System.out.println(cipher);
        System.out.println(plain);
    }
}
