import java.io.*;
import java.net.*;

public class P3 {

    public static void main(String[] args) {
        DatagramSocket udpSocket = null;
        Socket s = null;
        try {
            // --- UDP Send ---
            udpSocket = new DatagramSocket(2033); // (ephemeral)
            DatagramPacket packet = null; // in case of while loop
            DatagramPacket r = null;
            byte[] receiveData = new byte[1024];

            // --- TCP Client Side ---
            s = new Socket("localhost", 2004);
            ObjectOutputStream out = new ObjectOutputStream(
                s.getOutputStream()
            );
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());

            while (true) {
                // UDP receive from P2
                r = new DatagramPacket(receiveData, receiveData.length);
                udpSocket.receive(r); // Blocks until data arrives
                String received = new String(r.getData(), 0, r.getLength());
                System.out.println("Receive: " + received);

                // TCP send to P4
                out.writeObject(received);
                out.flush();

                // TCP receive from P4
                String response = (String) in.readObject(); // Receive
                System.out.println("Receive :" + response);

                // UDP send to P2
                packet = new DatagramPacket(
                    response.getBytes(),
                    response.getBytes().length,
                    InetAddress.getByName("localhost"),
                    2022
                );
                udpSocket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                if (s != null) s.close();
            } catch (IOException e) {}
            try {
                if (udpSocket != null) udpSocket.close();
            } catch (Exception e) {}
        }
    }
}
