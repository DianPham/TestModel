import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DES {

    StringAction sa = new StringAction();
    public String performEncryption(String plaintext, String key)
    {
        try {
            sa.writeFile(plaintext, "Des");
            sa.writeFile(null, "DeDes");
            sa.writeFile(null, "EnDes");
            FileInputStream fis = new FileInputStream(sa.disk + "Des.txt"); //Sửa ổ đĩa theo máy
            FileOutputStream fos = new FileOutputStream(sa.disk + "EnDes.txt"); //Sửa ổ đĩa theo máy
            encryptOrDecrypt(key,Cipher.ENCRYPT_MODE, fis, fos);
            return sa.openFile("EnDes");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public String performDecryption(String key)
    {
        FileInputStream fis2 = null;
        try {
            fis2 = new FileInputStream(sa.disk + "EnDes.txt"); //Sửa ổ đĩa theo máy
            FileOutputStream fos2 = new FileOutputStream(sa.disk + "DeDes.txt"); //Sửa ổ đĩa theo máy
            encryptOrDecrypt(key,Cipher.DECRYPT_MODE, fis2, fos2);
            return sa.openFile("DeDes");
        } catch(Throwable ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable{
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");

        if(mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, desKey);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }
    private static void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }

    public static void main(String[] args) {
        DES des = new DES();
        String cipher = des.performEncryption("I LOVE YOU MORE THAN I CAN SAY, hoangdan", "baomatthongtin");
        String plain = des.performDecryption("baomatthongtin");
        System.out.println(cipher);
        System.out.println(plain);
    }
}
