import java.io.*;
import java.net.*;
import java.rmi.registry.*;

public class P1 extends java.rmi.server.UnicastRemoteObject implements IP2 {

    public void Affichage(String message) throws Exception {
        System.out.println(message);
    }

    public P1() throws Exception {}

    public static void main(String[] args) {
        // Declare Sockets here
        Socket s = null;

        try {
            s = new Socket("localhost", 2002);
            // --- Client Side ---
            ObjectOutputStream out = new ObjectOutputStream(
                s.getOutputStream()
            );

            // --- Sending/Receiving (Both sides) ---
            String str = Integer.toString(25);
            out.writeObject(str); // Send

            // receive from P4
            P1 p1 = new P1();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Refp", p1);
            System.out.println("P1 is waiting for P4");
        } catch (Exception e) {
            System.out.println("P1: " + e.toString());
        } finally {
            try {
                if (s != null) s.close();
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.toString());
            }
        }
    }
}
