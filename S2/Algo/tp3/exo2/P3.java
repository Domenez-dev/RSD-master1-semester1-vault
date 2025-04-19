import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets; // For String to bytes[] need

public class P3 {

    public static void main(String[] args) {
        // Declare Sockets here
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(3003);
            byte[] receiveData = new byte[1024];
            DatagramPacket q = null;
            q = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(q); // Blocks until data arrives
            String received = new String(q.getData(), 0, q.getLength());
            System.out.println("Receive: " + received);

            // send to P4
            byte[] sendData = Integer.toString(
                (Integer.parseInt(received.trim()) * 3)
            ).getBytes(StandardCharsets.UTF_8);
            DatagramPacket sendPacket = null;

            sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                InetAddress.getByName("localhost"),
                3004
            );
            socket.send(sendPacket);

            // --- Receiving from P4 ---
            byte[] receiveDataP4 = new byte[1024];

            q = new DatagramPacket(receiveDataP4, receiveDataP4.length);
            socket.receive(q); // Blocks until data arrives
            String receivedP4String = new String(q.getData(), 0, q.getLength());
            System.out.println("Receive from P4: " + receivedP4String);
            sendData = receivedP4String.getBytes(StandardCharsets.UTF_8);

            // -- Sending to P2
            sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                InetAddress.getByName("localhost"),
                3002
            );
            socket.send(sendPacket);
        } catch (Exception e) {
            System.out.println("P3: " + e.toString());
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.toString());
            }
        }
    }
}
