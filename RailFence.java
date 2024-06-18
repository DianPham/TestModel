public class RailFence
{
    public String performEncryption(String plaintext, String key)
    {
        int k = Integer.valueOf(key);
        int n = plaintext.length();
        int sd = k;
        int sc = n % sd == 0 ? n / sd : n / sd + 1;
        char[][] hr = new char[sd][sc];
        int c = 0, d = 0;
        for (int i = 0; i < n; i++) {
            hr[d][c] = plaintext.charAt(i);
            d++;
            if (d == k) {
                d = 0;
                c++;
            }
        }
        String result = "";
        for (int i = 0; i < sd; i++) {
            for (int j = 0; j < sc; j++) {
                if (hr[i][j] != 0) {
                    result += hr[i][j];
                }
            }
        }

        return result;
    }

    public String performDecryption(String cipher, String key)
    {
        int k = Integer.valueOf(key);
        int n = cipher.length();
        int sd = k;
        int sc = n % sd == 0 ? n / sd : n / sd + 1;
        char[][] hr = new char[sd][sc];
        int index = 0;
        for (int i = 0; i < sd; i++) {
            for (int j = 0; j < sc; j++) {
                if (index < cipher.length()) {
                    hr[i][j] = cipher.charAt(index++);
                }
            }
        }
        String result = "";
        int c = 0, d = 0;
        for (int i = 0; i < n; i++) {
            result += hr[d][c];
            d++;
            if (d == k) {
                d = 0;
                c++;
            }
        }
        return result;

    }
    public static void main(String[] args)
    {
        RailFence rf = new RailFence();
        String cipher = rf.performEncryption("hoangdan", "3");
        String plain = rf.performDecryption(cipher, "3");
        System.out.println(cipher);
        System.out.println(plain);
    }
}
