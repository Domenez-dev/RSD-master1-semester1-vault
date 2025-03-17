import java.io.*;
import java.net.*;

public class P2 {
    public static void main(String[] args) {
        // TCP P1-P2
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("waiting for P1...");

            while (true) {
                Socket socket = serverSocket.accept();
                    System.out.println("P1 connected");

                    // Receive message from P1
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = in.readLine();
                    System.out.println("Received from P1: " + message);

                    // Modify the message
                    String modifiedMessage = Integer.toString(Integer.parseInt(message) * 2);

                    // UDP send avec P3
                    DatagramSocket udpSocket = new DatagramSocket();
                    InetAddress p3Address = InetAddress.getByName("localhost");
                    byte[] sendData = modifiedMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, p3Address, 6000);
                    udpSocket.send(sendPacket);
                    System.out.println("Sent to P3: " + modifiedMessage);

                    // UDP receive de P3
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    udpSocket.receive(receivePacket);
                    String responseFromP3 = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received depuis P3: " + responseFromP3);

                    // Send back to P1
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(responseFromP3);

                    udpSocket.close();
            }
        } catch(Exception e) {System.out.println("P1"+e.toString());}
    }
}
