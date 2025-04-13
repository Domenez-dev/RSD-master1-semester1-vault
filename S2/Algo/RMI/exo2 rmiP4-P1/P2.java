import java.io.*;
import java.net.*;

public class P2 {

    public static void main(String[] args) {
        // Declare Sockets here
        ServerSocket serverSocket = null;
        DatagramSocket socket = null;
        Socket p4_socketSocket = null;

        try {
            serverSocket = new ServerSocket(2002);
            socket = new DatagramSocket(3002);
            p4_socketSocket = new Socket("localhost", 2004);

            // --- Server Side ---
            Socket c = serverSocket.accept(); // Waits for client
            ObjectOutputStream out = new ObjectOutputStream(
                c.getOutputStream()
            );
            ObjectInputStream in = new ObjectInputStream(c.getInputStream());

            // --- Sending/Receiving (Both sides) ---
            String response = (String) in.readObject(); // Receive
            System.out.println("Received :" + response);
            int num = Integer.parseInt(response.trim()) * 2;

            byte[] sendData = Integer.toString(num).getBytes();
            DatagramPacket sendPacket = null;

            // send to P3
            sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                InetAddress.getByName("localhost"),
                3003
            );
            socket.send(sendPacket);

            // send to P4
            String message = Integer.toString(num);
            ObjectOutputStream outP4Stream = new ObjectOutputStream(
                p4_socketSocket.getOutputStream()
            );
            outP4Stream.writeObject(message);

            // receive from P3
            byte[] receiveData = new byte[1024];
            DatagramPacket q = null;

            q = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(q); // Blocks until data arrives
            String received = new String(q.getData(), 0, q.getLength());
            System.out.println("Receive from P3: " + received);

            // send to P1
            out.writeObject(received);
        } catch (Exception e) {
            System.out.println("P2: " + e.toString());
        } finally {
            try {
                if (serverSocket != null) serverSocket.close();
                if (socket != null) socket.close();
                if (p4_socketSocket != null) p4_socketSocket.close();
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.toString());
            }
        }
    }
}
