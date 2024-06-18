public class PlayFail {
    char pf[][] = {
            {'M','O','N','A','R'},
            {'C','H','Y','B','D'},
            {'E','F','G','I','K'},
            {'L','P','Q','S','T'},
            {'U','V','W','X','Z'}
    };

    public String performEncryption(String plaintext)
    {
        String text = plaintext.toUpperCase().replace('J', 'I');
        return encrypt(text);
    }

    public String performDecryption(String cipher)
    {
        return decrypt(cipher);
    }

    private String encrypt(String plaintext) {
        int n = plaintext.length();
        String cipher = "";
        char a, b;
        for (int i = 0; i < n; i += 2) {
            if (Character.isLetter(plaintext.charAt(i)))
            {
                a = plaintext.charAt(i);
                b = (i + 1 < n) ? plaintext.charAt(i + 1) : 'X';
                if (a == b) {
                    b = 'X';
                }
                cipher += replace(a, b);
            }
            else
            {
                cipher += plaintext.charAt(i);
            }
        }
        return cipher;
    }
    private String decrypt(String message) {
        StringBuilder decodedMessage = new StringBuilder();

        // Process the message in digraphs
        for (int i = 0; i < message.length(); i += 2) {
            char a = message.charAt(i);
            char b = message.charAt(i + 1);
            int[] aPos = getCharPos(a);
            int[] bPos = getCharPos(b);

            if (aPos[0] == bPos[0]) {
                // Same row
                decodedMessage.append(pf[aPos[0]][(aPos[1] + 4) % 5]);
                decodedMessage.append(pf[bPos[0]][(bPos[1] + 4) % 5]);
            } else if (aPos[1] == bPos[1]) {
                // Same column
                decodedMessage.append(pf[(aPos[0] + 4) % 5][aPos[1]]);
                decodedMessage.append(pf[(bPos[0] + 4) % 5][bPos[1]]);
            } else {
                // Rectangle swap
                decodedMessage.append(pf[aPos[0]][bPos[1]]);
                decodedMessage.append(pf[bPos[0]][aPos[1]]);
            }
        }

        return decodedMessage.toString();
    }
    private String replace(char a, char b) {
        String vta = locate(a);
        String vtb = locate(b);
        char x, y;
        if (vta.charAt(1) == vtb.charAt(1)) { // Cùng cột
            x = pf[(vta.charAt(0) - '0' + 1) % 5][vta.charAt(1) - '0'];
            y = pf[(vtb.charAt(0) - '0' + 1) % 5][vtb.charAt(1) - '0'];
        } else if (vta.charAt(0) == vtb.charAt(0)) { // Cùng hàng
            x = pf[vta.charAt(0) - '0'][(vta.charAt(1) - '0' + 1) % 5];
            y = pf[vtb.charAt(0) - '0'][(vtb.charAt(1) - '0' + 1) % 5];
        } else { // Không cùng hàng hoặc cột
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
        return ""; // Nếu ký tự không tồn tại trong ma trận
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
        String cipher = pf.performEncryption("hoangdan");
        String plain = pf.performDecryption(cipher);
        System.out.println(cipher);
        System.out.println(plain);
    }
}
