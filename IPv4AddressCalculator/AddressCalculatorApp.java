import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Calculation{
    static int[] ipv4 = new int[4];
    static int[] subnet = new int[4];
    static int[] net = new int[4];
    static int[] broad = new int[4];
    static int[] first = new int[4];
    static int[] last = new int[4];
    static int[] prefkey = { 255, 254, 252, 248, 240, 224, 192, 128, 0 };
    static int[] prefval = { 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    static int[] addrval = { 1, 2, 4, 8, 16, 32, 64, 128, 256 };
    static int prefix = 0;
    static int hosts = 1;

    public static String inttoString(int[] arr) {
        String res="";
        for (int i = 0; i <3 ; i++) {
            res+=(Integer.toString(arr[i])+".");
        }
        res+=Integer.toString(arr[3]);
        return res;
    }
    public static void stringToInt(String[] starr, int[] intarr) {
        for (int i = 0; i < 4; i++) {
            intarr[i] = Integer.parseInt(starr[i]);
        }
    }

    public static void networkIP() {
        int flag = 0;
        for (int i = 0; i < 4; i++) {
            if (flag == 0 && subnet[i] != 255) {
                first[i] = (ipv4[i] & subnet[i]) + 1;
            } else {
                first[i] = ipv4[i];
            }
            if (subnet[i] != 255) {
                net[i] = ipv4[i] & subnet[i];
                for (int j = 0; j < 9; j++) {
                    if (subnet[i] == prefkey[j]) {
                        prefix += prefval[j];
                    }
                }
            } else {
                net[i] = ipv4[i];
                prefix += 8;

            }
        }
    }

    public static void broadcastIp() {
        for (int i = 0; i < 4; i++) {
            if (subnet[i] == 255) {
                broad[i] = ipv4[i];
                hosts *= 1;
            } else {
                for (int j = 0; j < 9; j++) {
                    if (subnet[i] == prefkey[j]) {
                        broad[i] = net[i] + (addrval[j] - 1);
                        hosts *= addrval[j];
                    }
                }
            }
        }
    }
}
class AppElements{
static JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
static JTextField t1,t2,t3,t4,t5,t6,t7,t8;
static JButton btn1,btn2;
static String[] s1,s2;
AppElements() {
    JFrame jf = new JFrame("IPv4 Address Calculator");

    btn1=new JButton("calculate");
    btn2=new JButton("clear");
   
    l1 = new JLabel("Network :");
    l2 = new JLabel("Prefix:");
    l3 = new JLabel("First :");
    l4 = new JLabel("Last :");
    l5 = new JLabel("Broadcast :");
    l6 = new JLabel("Hosts :");
    
    l7 = new JLabel("Enter the values below and press \"calculate\"");
    l8 = new JLabel("IP :");
    l9 = new JLabel("Subnet :");

    t1 = new JTextField("0.0.0.0");
    t2 = new JTextField("00");
    t3 = new JTextField("0.0.0.0");
    t4 = new JTextField("0.0.0.0");
    t5 = new JTextField("0.0.0.0");
    t6 = new JTextField("0");

    t7 = new JTextField("0.0.0.0");
    t8 = new JTextField("0.0.0.0");

    l1.setBounds(30, 30, 200, 25);
    t1.setBounds(150, 30, 120, 25);
    l2.setBounds(30, 60, 200, 25);
    t2.setBounds(150, 60, 30, 25);
    l3.setBounds(30, 90, 200, 25);
    t3.setBounds(150, 90, 120, 25);
    l4.setBounds(30, 120, 200, 25);
    t4.setBounds(150, 120, 120, 25);
    l5.setBounds(30, 150, 200, 25);
    t5.setBounds(150, 150, 120, 25);
    l6.setBounds(30, 180, 200, 25);
    t6.setBounds(150, 180, 70, 25);

    l7.setBounds(20, 230,300,25);

    l8.setBounds(30,270, 200, 25);
    t7.setBounds(150,270,120,25);
    l9.setBounds(30, 300,200,25);
    t8.setBounds(150, 300, 120, 25);

    btn1.setBounds(180, 350, 100, 30);
    btn2.setBounds(80,350,80,30);

    jf.add(l1);
    jf.add(t1);
    jf.add(l2);
    jf.add(t2);
    jf.add(l3);
    jf.add(t3);
    jf.add(l4);
    jf.add(t4);
    jf.add(l5);
    jf.add(t5);
    jf.add(l6);
    jf.add(t6);
    jf.add(l7);
    jf.add(l8);
    jf.add(t7);
    jf.add(l9);
    jf.add(t8);
    jf.add(btn1);
    jf.add(btn2);

    jf.setSize(350, 420);
    jf.setLayout(null);
    jf.setVisible(true);

    btn1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            s1=(t7.getText()).split("\\.");
            s2=(t8.getText()).split("\\.");
            Calculation.stringToInt(s1, Calculation.ipv4);
            Calculation.stringToInt(s2, Calculation.subnet);
            Calculation.networkIP();
            //System.out.println(Calculation.prefix);
            Calculation.broadcastIp();
            //System.out.println(Calculation.hosts);
            t1.setText(Calculation.inttoString(Calculation.net));
            t2.setText(Integer.toString(Calculation.prefix));
            t3.setText(Calculation.inttoString(Calculation.first));
            t4.setText(Integer.toString(Calculation.broad[0])+"."+Integer.toString(Calculation.broad[1])+"."+Integer.toString(Calculation.broad[2])+"."+Integer.toString((Calculation.broad[3])-1));
            t5.setText(Calculation.inttoString(Calculation.broad));
            t6.setText(Integer.toString((Calculation.hosts)-2));
        }
    });
btn2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            t1.setText("0.0.0.0");
            t2.setText("00");
            t3.setText("0.0.0.0");
            t4.setText("0.0.0.0");
            t5.setText("0.0.0.0");
            t6.setText("0.0.0.0");
            t7.setText("0.0.0.0");
            t8.setText("0.0.0.0");
        }
    });
}

}
class AddressCalculatorApp{
    public static void main(String[] args) {
        new AppElements();
    }
}
