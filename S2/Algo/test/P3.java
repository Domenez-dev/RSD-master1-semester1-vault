import java.io.*;
import java.net.*;
import java.rmi.registry.*;

public class P3 extends java.rmi.server.UnicastRemoteObject implements IP3 {

    public P3() throws Exception {
        super();
    }

    public int Add(int s2, int n) throws Exception {
        return (s2 * 3 + n);
    }

    public void Affichage(String message) throws Exception {
        System.out.println(message);
    }

    public void SendToP4(String s3) throws Exception {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1098);
        IP4 i = (IP4) registry.lookup("Refp");
        int x = i.Add(Integer.parseInt(s3.trim()));
        System.out.println();
        i.Affichage(Integer.toString(x));
    }

    public void SendToP2(String x) throws Exception {
        // --- UDP Send ---
        DatagramSocket socket = new DatagramSocket(); // (ephemeral)
        DatagramPacket packet = null; // in case of while loop

        String msg = x;
        byte[] data = msg.getBytes();
        packet = new DatagramPacket(
            data, // data.getBytes()
            data.length,
            InetAddress.getByName("localhost"),
            3002
        );
        socket.send(packet);
    }

    public static void main(String[] args) throws Exception {
        try {
            P3 p3 = new P3();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Refp", p3);
            System.out.println("P3 is ready");
        } catch (Exception e) {
            System.err.println("P3 exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
