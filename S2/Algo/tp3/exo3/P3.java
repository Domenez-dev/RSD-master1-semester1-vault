import java.io.*;
import java.net.*;
import java.util.*;

public class P3 {
    public static void main(String[] args) {
        try {
            // TCP Server for P1 -> P3
            ServerSocket tcpServerForP1 = new ServerSocket(2001); // Listening on 2001 for P1
            System.out.println("P3 waiting for TCP connection from P1...");
            Socket tcpFromP1 = tcpServerForP1.accept();
            ObjectInputStream tcpInFromP1 = new ObjectInputStream(tcpFromP1.getInputStream());
            
            // TCP Client for P3 -> P1
            Socket tcpClientToP1 = new Socket("localhost", 2002); // Connecting to P1 on 2002
            ObjectOutputStream tcpOutToP1 = new ObjectOutputStream(tcpClientToP1.getOutputStream());
            
            // UDP with P4
            DatagramSocket udpSocket = new DatagramSocket(9878); // P3 listens on 9878 for P4
            InetAddress p4Address = InetAddress.getByName("localhost");
            int p4Port = 9879;
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                // Check for message from P1 (TCP)
                if (tcpFromP1.getInputStream().available() > 0) {
                    String fromP1 = (String) tcpInFromP1.readObject();
                    System.out.println("Received from P1: " + fromP1);
                    
                    // Process and send response to P1
                    String response = "P3 processed: " + fromP1.toUpperCase();
                    tcpOutToP1.writeObject(response);
                }
                
                // Check for message from P4 (UDP)
                byte[] udpReceiveData = new byte[1024];
                DatagramPacket udpReceivePacket = new DatagramPacket(udpReceiveData, udpReceiveData.length);
                udpSocket.receive(udpReceivePacket); // This blocks, might want to use timeout or separate thread
                
                String fromP4 = new String(udpReceivePacket.getData(), 0, udpReceivePacket.getLength());
                System.out.println("Received from P4: " + fromP4);
                
                // Process and send response to P4
                String udpResponse = "P3 response to P4: " + fromP4.length() + " chars";
                byte[] udpSendData = udpResponse.getBytes();
                DatagramPacket udpSendPacket = new DatagramPacket(
                    udpSendData, udpSendData.length,
                    udpReceivePacket.getAddress(),
                    udpReceivePacket.getPort());
                udpSocket.send(udpSendPacket);
            }
        } catch (Exception e) {
            System.out.println("P3 error: " + e.toString());
        }
    }
}
