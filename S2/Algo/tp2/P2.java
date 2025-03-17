package tp2;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class P2 { // client
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Enter message: ");
                String ch = sc.nextLine(); 
                byte[] sendT = ch.getBytes(); 

                byte[] ipAddr = new byte[] { (byte) 192, (byte) 168, (byte) 207, (byte) 177 };
                InetAddress ip = InetAddress.getByAddress(ipAddr);
                DatagramPacket p = new DatagramPacket(sendT, sendT.length, ip, 2008);
                s.send(p); 
                System.out.println("Message sent: " + ch);

                // Prepare to receive a response from the server
                byte[] receiveT = new byte[1024]; // Increased buffer size for flexibility
                DatagramPacket q = new DatagramPacket(receiveT, receiveT.length);
                s.receive(q); // Wait for a response
                String str = new String(q.getData(), 0, q.getLength()); // Convert bytes to string
                System.out.println("Received: " + str);
            }
        } catch (Exception e) {
            System.out.println("P2: " + e.toString());
        }
    }
}
