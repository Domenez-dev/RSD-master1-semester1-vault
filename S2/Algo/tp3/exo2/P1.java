import java.io.*;
import java.net.*;

public class P1 {

    public static void main(String[] args) {
        // Declare Sockets here
        Socket s = null;

        try {
            s = new Socket("localhost", 2002);
            // --- Client Side ---
            ObjectOutputStream out = new ObjectOutputStream(
                s.getOutputStream()
            );
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());

            // --- Sending/Receiving (Both sides) ---
            String str = Integer.toString(25);
            out.writeObject(str); // Send
            String response = (String) in.readObject(); // Receive
            System.out.println("Receive :" + response);
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
