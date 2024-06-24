public class PlayFail {
    char pf[][] = {
            {'M','O','N','A','R'},
            {'C','H','Y','B','D'},
            {'E','F','G','I','K'},
            {'L','P','Q','S','T'},
            {'U','V','W','X','Z'}
    };

    public String performEncryption(String plaintext) {
        String text = plaintext.toUpperCase().replace('J', 'I');
        return encrypt(text);
    }

    public String performDecryption(String cipher) {
        return decrypt(cipher);
    }

    private String encrypt(String plaintext) {
        int n = plaintext.length();
        StringBuilder cipher = new StringBuilder();
        char a, b;
        for (int i = 0; i < n; i += 2) {
            if (Character.isLetter(plaintext.charAt(i))) {
                a = plaintext.charAt(i);
                b = (i + 1 < n && Character.isLetter(plaintext.charAt(i + 1))) ? plaintext.charAt(i + 1) : 'X';
                if (a == b) {
                    b = 'X';
                    i--; // Stay on the current character for the next pair
                }
                cipher.append(replace(a, b));
            } else {
                cipher.append(plaintext.charAt(i));
                i--; // Stay on the current character for the next pair
            }
        }
        return cipher.toString();
    }

    private String decrypt(String message) {
        StringBuilder decodedMessage = new StringBuilder();
        int n = message.length();
        for (int i = 0; i < n; i += 2) {
            if (Character.isLetter(message.charAt(i))) {
                char a = message.charAt(i);
                char b = (i + 1 < n && Character.isLetter(message.charAt(i + 1))) ? message.charAt(i + 1) : 'X';
                decodedMessage.append(replaceDecode(a, b));
            } else {
                decodedMessage.append(message.charAt(i));
                i--; // Stay on the current character for the next pair
            }
        }
        return decodedMessage.toString();
    }

    private String replace(char a, char b) {
        String vta = locate(a);
        String vtb = locate(b);
        char x, y;
        if (vta.charAt(1) == vtb.charAt(1)) { // Same column
            x = pf[(vta.charAt(0) - '0' + 1) % 5][vta.charAt(1) - '0'];
            y = pf[(vtb.charAt(0) - '0' + 1) % 5][vtb.charAt(1) - '0'];
        } else if (vta.charAt(0) == vtb.charAt(0)) { // Same row
            x = pf[vta.charAt(0) - '0'][(vta.charAt(1) - '0' + 1) % 5];
            y = pf[vtb.charAt(0) - '0'][(vtb.charAt(1) - '0' + 1) % 5];
        } else { // Rectangle swap
            x = pf[vta.charAt(0) - '0'][vtb.charAt(1) - '0'];
            y = pf[vtb.charAt(0) - '0'][vta.charAt(1) - '0'];
        }
        return x + "" + y;
    }

    private String replaceDecode(char a, char b) {
        String vta = locate(a);
        String vtb = locate(b);
        char x, y;
        if (vta.charAt(1) == vtb.charAt(1)) { // Same column
            x = pf[(vta.charAt(0) - '0' + 4) % 5][vta.charAt(1) - '0'];
            y = pf[(vtb.charAt(0) - '0' + 4) % 5][vtb.charAt(1) - '0'];
        } else if (vta.charAt(0) == vtb.charAt(0)) { // Same row
            x = pf[vta.charAt(0) - '0'][(vta.charAt(1) - '0' + 4) % 5];
            y = pf[vtb.charAt(0) - '0'][(vtb.charAt(1) - '0' + 4) % 5];
        } else { // Rectangle swap
            x = pf[vta.charAt(0) - '0'][vtb.charAt(1) - '0'];
            y = pf[vtb.charAt(0) - '0'][vta.charAt(1) - '0'];
        }
        return x + "" + y;
    }

    private String locate(char a) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pf[i][j] == a) {
                    return i + "" + j;
                }
            }
        }
        return ""; // If the character does not exist in the matrix
    }

    private int[] getCharPos(char c) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (pf[row][col] == c) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        PlayFail pf = new PlayFail();
        String cipher = pf.performEncryption("hoangdan,hoangdanpham");
        String plain = pf.performDecryption(cipher);
        System.out.println("Cipher Text: " + cipher);
        System.out.println("Plain Text: " + plain);
    }
}
