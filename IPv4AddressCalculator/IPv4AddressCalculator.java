
import java.io.*;
public class IPv4AddressCalculator {
    static int[] ipv4 = new int[4];
    static int[] subnet = new int[4];
    static int[] net = new int[4];
    static int[] broad = new int[4];
    static int[] first = new int[4];
    static int[] last = new int[4];
    static int[] prefkey={255,254,252,248,240,224,192,128,0};
    static int[] prefval={8,7,6,5,4,3,2,1,0};
    static int[] addrval={1,2,4,8,16,32,64,128,256}; 
    static int prefix = 0;   
    static int hosts = 1;
    public static void ipPrint(String name,int addr[]) {
        System.out.print(name);
        for (int i = 0;i<3;i++) {
        System.out.print(addr[i]+".");
    }
    System.out.print(addr[3]+"\n");
    }
    public static void stringToInt(String[] starr,int[] intarr) {
        for (int i = 0; i <4; i++) {
            intarr[i]=Integer.parseInt(starr[i]);
        }
    }
    public static void networkIP() {
        int flag=0;
        for (int i = 0; i <4; i++) {
            if (flag==0&&subnet[i]!=255) {
                first[i]= (ipv4[i] & subnet[i])+1;
            } else {
                first[i]=ipv4[i];
            }
            if (subnet[i]!=255) {
                net[i]=ipv4[i]&subnet[i];
                for (int j = 0; j<9 ; j++) {
                    if(subnet[i]==prefkey[j]){
                        prefix+=prefval[j];
                    }
                }
            }else{
                net[i]=ipv4[i];
                prefix += 8;

            }
        }
    }
    public static void broadcastIp() {
        for (int i = 0; i<4; i++) {
            if (subnet[i]==255) {
                broad[i]=ipv4[i];
                hosts*=1;
            } else {
                for (int j = 0; j<9; j++) {
                    if (subnet[i]==prefkey[j]) {
                        broad[i]=net[i]+(addrval[j]-1);
                        hosts*=addrval[j];
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        String[] ip,su;
        String inp;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter IP : ");
            inp = br.readLine();
            ip=inp.split("\\.");
            System.out.print("Enter Subnet : ");
            inp=br.readLine();
            su=inp.split("\\.");
            
            stringToInt(ip, ipv4);
            stringToInt(su, subnet);
            System.out.println("\nResult :\n");
            networkIP();
            broadcastIp();
            ipPrint("Network IP : ",net);
            ipPrint("First usable IP : ", first);
            System.out.println("Last usable IP : "+Integer.toString(broad[0])+"."+ Integer.toString(broad[1])+"."+Integer.toString(broad[2])+"."+ Integer.toString(broad[3]-1));
            ipPrint("Broadcast IP : ", broad);
            System.out.println("Prefix : "+prefix);
            System.out.println("Hosts : "+Integer.toString(hosts-2));

        } catch (IOException ie) {
            System.out.println(ie);
        }
    }
}