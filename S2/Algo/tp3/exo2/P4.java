import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets; // For String to bytes[] need

public class P4 {

    public static void main(String[] args) {
        // Declare Sockets here
        ServerSocket serverSocket = null;
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(3004);
            serverSocket = new ServerSocket(2004);

            // --- Server Side ---
            Socket c = serverSocket.accept(); // Waits for client
            ObjectInputStream in = new ObjectInputStream(c.getInputStream());

            String N = (String) in.readObject();
            System.out.println("Receive N :" + N);

            // --- Receiving from P4 ---
            byte[] receiveData = new byte[1024];
            DatagramPacket q = null;

            q = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(q); // Blocks until data arrives
            String M = new String(q.getData(), 0, q.getLength());
            System.out.println("Receive M : " + M);

            // --- Sending to P3 ---
            int S = Integer.parseInt(M) * Integer.parseInt(N);
            System.out.println("sending S : " + S);

            byte[] sendData = Integer.toString(S).getBytes(
                StandardCharsets.UTF_8
            );
            DatagramPacket sendPacket = null;
            sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                InetAddress.getByName("localhost"),
                3003
            );
            socket.send(sendPacket);
        } catch (Exception e) {
            System.out.println("P4: " + e.toString());
        } finally {
            try {
                if (socket != null) socket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.toString());
            }
        }
    }
}
