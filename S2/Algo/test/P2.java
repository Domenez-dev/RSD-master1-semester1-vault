import java.io.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class P2 {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket c = null;

        DatagramSocket socket = null;

        try {
            // --- Server Side avev P1 ---
            serverSocket = new ServerSocket(2002);
            c = serverSocket.accept(); // Waits for client
            ObjectOutputStream out = new ObjectOutputStream(
                c.getOutputStream()
            );
            ObjectInputStream in = new ObjectInputStream(c.getInputStream());

            socket = new DatagramSocket(3002);
            byte[] buffer = new byte[1024];
            DatagramPacket q = new DatagramPacket(buffer, buffer.length);

            while (true) {
                // Receive from P1
                String response = (String) in.readObject(); // Receive
                System.out.println("Receive :" + response);
                int n = Integer.parseInt(response.trim());
                int s2 = n * 2;
                System.out.println("n: " + n + " S2: " + s2);

                // RMI send to P3
                Registry registry = LocateRegistry.getRegistry(
                    "127.0.0.1",
                    1099
                );
                IP3 i = (IP3) registry.lookup("Refp");
                int x = i.Add(s2, n);
                System.out.println();
                i.Affichage(Integer.toString(x));
                i.SendToP4(Integer.toString(x));

                socket.receive(q); // Waits for data
                String received = new String(q.getData(), 0, q.getLength());
                System.out.println("Receive: " + received);

                out.writeObject(x);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        } finally {
            try {
                if (serverSocket != null) serverSocket.close();
                if (c != null) c.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Error closing sockets: " + e.toString());
            }
        }
    }
}
