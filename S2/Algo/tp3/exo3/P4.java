import java.net.*;
import java.util.*;

public class P4 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9879); // P4 listens on 9879
            InetAddress p3Address = InetAddress.getByName("localhost");
            int p3Port = 9878;
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.print("Enter message for P3: ");
                String message = scanner.nextLine();
                
                // Send to P3
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length,
                    p3Address, p3Port);
                socket.send(sendPacket);
                
                // Receive from P3
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from P3: " + received);
            }
        } catch (Exception e) {
            System.out.println("P4 error: " + e.toString());
        }
    }
}
