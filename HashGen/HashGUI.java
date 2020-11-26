/*
MIT License
Copyright (c) 2020 Viruchith Ganesan
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashGenerationException extends RuntimeException {
    public HashGenerationException(String message, Exception ex) {
        super(message, ex);
    }
}

class Hasher {

    private static String hashFromFile(File file, String algorithm) throws HashGenerationException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            byte[] bytesBuffer = new byte[1024];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
                digest.update(bytesBuffer, 0, bytesRead);
            }

            byte[] hashedBytes = digest.digest();

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | IOException ex) {
            throw new HashGenerationException("Could not generate hash from file", ex);
        }
    }

    private static String hashFromString(String message, String algorithm) throws HashGenerationException {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new HashGenerationException("Could not generate hash from String", ex);
        }
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }

    public static String getStringMD5(String message) throws HashGenerationException {
        return hashFromString(message, "MD5");
    }

    public static String getStringSHA1(String message) throws HashGenerationException {
        return hashFromString(message, "SHA-1");
    }

    public static String getStringSHA256(String message) throws HashGenerationException {
        return hashFromString(message, "SHA-256");
    }

    public static String getFileMD5(File file) throws HashGenerationException {
        return hashFromFile(file, "MD5");
    }

    public static String getFileSHA1(File file) throws HashGenerationException {
        return hashFromFile(file, "SHA-1");
    }

    public static String getFileSHA256(File file) throws HashGenerationException {
        return hashFromFile(file, "SHA-256");
    }

}

public class HashGUI extends JFrame{
    
    JLabel string_label,file_label,md5_label,sha1_label,sha256_label,file_path_label,author_label;  
    JTextField string_inp,md5_inp,sha1_inp,sha256_inp;
    JButton str_button,file_button;
    JPanel string_panel,file_panel;

    private void renderStringPanel(){
        // String panel and Components
        string_panel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(220, 120);
            };
        };
        string_panel.setLayout(null);

        // Button
        str_button = new JButton("Generate");
        str_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (string_inp.getText().equals("") == false) {
                    // clear label
                    file_path_label.setText("no file");

                    String message = string_inp.getText();
                    Hasher hasher = new Hasher();

                    md5_inp.setText(hasher.getStringMD5(message));
                    sha1_inp.setText(hasher.getStringSHA1(message));
                    sha256_inp.setText(hasher.getStringSHA256(message));
                } else {
                    file_path_label.setText("no file");
                    clearHasValues();
                }
            }
        });

        string_label = new JLabel("Enter text : ");
        string_inp = new JTextField();

        string_panel.add(string_label);
        string_panel.add(string_inp);
        string_panel.add(str_button);

        // String panel
        string_label.setBounds(25, 20, 100, 10);
        string_inp.setBounds(25, 40, 200, 20);
        str_button.setBounds(90, 80, 70, 20);

    }


    private void renderFilePanel(){
        file_panel = new JPanel() {
        @Override
        public Dimension getPreferredSize() {
                return new Dimension(220, 120);
            };
        };
        file_panel.setLayout(null);

        file_button = new JButton("Browse");
        file_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(new JFrame());
                if (i == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    file_path_label.setText(file.getName());

                    Hasher hasher = new Hasher();

                    md5_inp.setText(hasher.getFileMD5(file));
                    sha1_inp.setText(hasher.getFileSHA1(file));
                    sha256_inp.setText(hasher.getFileSHA256(file));

                }
            }
        });

        file_panel.add(file_button);
        file_panel.add(file_path_label);

        // File Panel
        file_button.setBounds(90, 50, 70, 20);
        file_path_label.setBounds(90, 80, 250, 15);

    }


    HashGUI(){
        super("HashGen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //File Panel
        file_path_label= new JLabel("no file");
        
        md5_label = new JLabel("MD5 :");
        sha1_label = new JLabel("SHA-1 :");
        sha256_label = new JLabel("SHA-256 :");

        author_label = new JLabel("@Viruchith");

        renderStringPanel();
        renderFilePanel();

        md5_inp = new JTextField();
        sha1_inp = new JTextField();
        sha256_inp = new JTextField();

        add(md5_label);
        add(md5_inp);
        add(sha1_label);
        add(sha1_inp);
        add(sha256_label);
        add(sha256_inp);

        add(author_label);

        // Tabbed Pane
        JTabbedPane tabbed_pane = new JTabbedPane();
        tabbed_pane.setBounds(20,20,270,160);

        // panel bounds
        string_panel.setBounds(10,10,170,250);
        file_panel.setBounds(10,10,170,250);
        



        // Hash Display Bounds
        md5_label.setBounds(50,190,100,10);
        md5_inp.setBounds(50,210,200,20);
        sha1_label.setBounds(50,240,100,10);
        sha1_inp.setBounds(50,260,200,20);
        sha256_label.setBounds(50,290,100,10);
        sha256_inp.setBounds(50,310,200,20);
    
        author_label.setBounds(110,350,100,15);


        tabbed_pane.add("Text",string_panel);
        tabbed_pane.add("File",file_panel);

        add(tabbed_pane);
        setSize(310,400);
        setLayout(null);          
        setVisible(true);        


    }

    private void clearHasValues(){
        md5_inp.setText("");
        sha1_inp.setText("");
        sha256_inp.setText("");
    }

    public static void main(String[] args) {
        new HashGUI();
    }

}

