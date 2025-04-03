import java.io.*;
import java.net.*;

public class P2 {

    public static void main(String[] args) {
        ServerSocket tcpSocket = null;
        DatagramSocket udpSocket = null;
        try {
            // --- Server Side TCP ---
            tcpSocket = new ServerSocket(2002);
            Socket c = tcpSocket.accept(); // Waits for client
            ObjectOutputStream out = new ObjectOutputStream(
                c.getOutputStream()
            );
            ObjectInputStream in = new ObjectInputStream(c.getInputStream());

            // --- UDP Send ---
            udpSocket = new DatagramSocket(2022); // (ephemeral)
            DatagramPacket packet = null; // in case of while loop
            DatagramPacket q = null;
            byte[] udpBuffer = new byte[1024];

            while (true) {
                // --- TCP with P1 ---
                String response = (String) in.readObject(); // Receive
                System.out.println("Receive :" + response);

                // --- UDP Send to P3 ---
                packet = new DatagramPacket(
                    response.getBytes(), // data.getBytes()
                    response.getBytes().length,
                    InetAddress.getByName("localhost"),
                    2033
                );
                udpSocket.send(packet);

                // --- UDP Receive from P3 ---
                q = new DatagramPacket(udpBuffer, udpBuffer.length);
                udpSocket.receive(q);
                String response2 = new String(q.getData(), 0, q.getLength());
                System.out.println("Receive :" + response2);

                out.writeObject(response2); // Send
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            // Cleanup
            try {
                if (tcpSocket != null) tcpSocket.close();
            } catch (IOException e) {}
            try {
                if (udpSocket != null) udpSocket.close();
            } catch (Exception e) {}
        }
    }
}
