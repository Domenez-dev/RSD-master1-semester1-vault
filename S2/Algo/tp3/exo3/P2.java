import java.net.*;
import java.util.*;

public class P2 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9877); // Listening on port 9877 for P1
            System.out.println("P2 UDP Server ready on port 9877");
            
            byte[] receiveData = new byte[1024];
            
            while (true) {
                // Receive from P1
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from P1: " + received);
                
                // Process and send response
                String response = "P2 response to: " + received;
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length,
                    receivePacket.getAddress(),
                    receivePacket.getPort());
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            System.out.println("P2 error: " + e.toString());
        }
    }
}
