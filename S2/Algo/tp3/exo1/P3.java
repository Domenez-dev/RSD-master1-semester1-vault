import java.net.*;
import java.io.*;

public class P3 {
    public static void main(String[] args) {
        try (DatagramSocket udpSocket = new DatagramSocket(6000)) {
            System.out.println("P3 waiting for UDP messages...");

            while (true) {
                // Receive message from P2
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                udpSocket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from P2: " + message);

                // Modify the message
                String modifiedMessage = Integer.toString(Integer.parseInt(message) * 3);

                // send modified tp P4
                Socket socket = new Socket("localhost", 7000);
                System.out.println("Connected to P4");
        
                    // send
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(modifiedMessage);

                    // receive from P4
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                System.out.println("Received from P4: " + response);



                // Send received message back to P2
                InetAddress p2Address = receivePacket.getAddress();
                int p2Port = receivePacket.getPort();
                byte[] sendData = modifiedMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, p2Address, p2Port);
                udpSocket.send(sendPacket);
                System.out.println("Sent to P2: " + modifiedMessage);
            }
        } catch(Exception e) {System.out.println("P1"+e.toString());}
    }
}
