package tp2;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class P1 { // server
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(9999);
            byte[] receiveT = new byte[20];
            DatagramPacket p = new DatagramPacket(receiveT, receiveT.length);

            while (true) {
                System.out.println("Waiting...");
                s.receive(p); 
                String str = new String(p.getData(), 0, p.getLength()); 
                System.out.println("Received: " + str);
                System.out.println("Port: " + p.getPort());
                System.out.println("Address: " + p.getAddress());
            }
        } catch (Exception e) {
            System.out.println("P1: " + e.toString());
        }
    }
}
