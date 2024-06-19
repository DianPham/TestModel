public class Vigenere {
    int vig[][];

    public Vigenere() {
        vig = new int[26][26];
        for (int i = 0; i < 26; i++)
            for (int j = 0; j < 26; j++)
                vig[i][j] = (i + j) % 26;
    }

    public String performEncryption(String plaintext, String key)
    {
        return en_de_crypt(plaintext, key);
    }

    public String performDecryption(String cipher, String key)
    {
        String newKey = reviseKey(key);
        return en_de_crypt(cipher, newKey);
    }

    private String en_de_crypt(String text, String key) {
        int n = text.length();
        String result = "";
        int k = 0;
        for (int i = 0; i < n; i++)
            if(Character.isLetter(text.charAt(i)))
            {
                result += encryptLetter(text.charAt(i), key.charAt(k));
                k++;
                k = k % key.length();
            }
            else
                result += text.charAt(i);
        return result;
    }

    private String reviseKey(String key1) {
        String key2 = "";
        int kn = key1.length();
        for (int i = 0; i < kn; i++)
            key2 += (char) (((26 - (Character.toUpperCase(key1.charAt(i)) - 'A')) % 26) + 'A');
        return key2;
    }

    private char encryptLetter(char c, int k) {
        int xn = Character.toUpperCase(c) - 'A';
        int kn = Character.toUpperCase(k) - 'A';
        int yn = vig[kn][xn];
        return (char)(yn + 'A');
        //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        Vigenere vig = new Vigenere();
        String cipher = vig.performEncryption("hoangdan", "hutech");
        String plain = vig.performDecryption(cipher, "hutech");
        System.out.println(cipher);
        System.out.println(plain);
    }
}
