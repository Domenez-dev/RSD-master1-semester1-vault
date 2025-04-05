import java.io.*;
import java.net.*;
import java.util.*;

public class P1 {
    public static void main(String[] args) {
        try {
            // UDP Connection with P2 (two-way)
            DatagramSocket udpSocket = new DatagramSocket(9876); // Using port 9876 for P1<->P2
            
            // TCP Connection to P3 (P1 -> P3)
            Socket tcpClientSocket = new Socket("localhost", 2001); // Connecting to P3 on port 2001
            ObjectOutputStream tcpOutToP3 = new ObjectOutputStream(tcpClientSocket.getOutputStream());
            
            // TCP Connection from P3 (P3 -> P1)
            ServerSocket tcpServer = new ServerSocket(2002); // P1 listening on port 2002 for P3
            System.out.println("P1 waiting for TCP connection from P3...");
            Socket tcpFromP3 = tcpServer.accept();
            ObjectInputStream tcpInFromP3 = new ObjectInputStream(tcpFromP3.getInputStream());
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\nP1 Menu:");
                System.out.println("1. Send message to P2 (UDP)");
                System.out.println("2. Send message to P3 (TCP)");
                System.out.print("Choose option: ");
                String option = scanner.nextLine();
                
                if (option.equals("1")) {
                    // UDP communication with P2
                    System.out.print("Enter message for P2: ");
                    String message = scanner.nextLine();
                    
                    // Send to P2
                    byte[] sendData = message.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length, 
                        InetAddress.getByName("localhost"), 9877); // P2 listens on 9877
                    udpSocket.send(sendPacket);
                    
                    // Receive from P2
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    udpSocket.receive(receivePacket);
                    String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received from P2: " + received);
                    
                } else if (option.equals("2")) {
                    // TCP communication to P3
                    System.out.print("Enter message for P3: ");
                    String message = scanner.nextLine();
                    tcpOutToP3.writeObject(message);
                    
                    // Receive from P3 (TCP)
                    String response = (String) tcpInFromP3.readObject();
                    System.out.println("Received from P3: " + response);
                }
            }
        } catch (Exception e) {
            System.out.println("P1 error: " + e.toString());
            e.printStackTrace();
        }
    }
}
