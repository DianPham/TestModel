import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import java.io.FileWriter;

public class StringAction {
    static public String disk = "C:\\Dian\\";


    public String joinString(String text1, String text2)
    {
        return text1 + "," + text2;
    }

    public String[] splitString(String text)
    {
        return text.split(",", 2);
    }

    public String openFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(disk + fileName + ".txt")); //Sửa ổ đĩa theo máy
            StringBuffer sb = new StringBuffer();
            char[] ca = new char[5];
            while (br.ready()) {
                int len = br.read(ca);
                sb.append(ca, 0, len);
            }
            br.close();
            JOptionPane.showMessageDialog(null, "Mở File thành công");
            return sb.toString();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Mở File không thành công: " + ex);
        }
        return null;
    }

    public void writeFile(String content, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(disk + fileName + ".txt")); //Sửa ổ đĩa theo máy
            bw.write(content);
            bw.close();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Ghi File không thành công: " + ex);
        }
    }

    public boolean compareString (String string1, String string2)
    {
        return string1.equals(string2);
    }

}
