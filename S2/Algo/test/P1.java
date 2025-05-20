import java.io.*;
import java.net.*;
import java.util.Scanner;

public class P1 {

    public static void main(String[] args) {
        // Declare Sockets here
        Scanner scanner = new Scanner(System.in);
        Socket s = null;

        try {
            // --- Client Side ---
            s = new Socket("localhost", 2002);
            ObjectOutputStream out = new ObjectOutputStream(
                s.getOutputStream()
            );
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());

            while (true) {
                System.out.print("Enter message: ");
                String userinput = scanner.nextLine();

                // --- Sending/Receiving (Both sides) ---
                out.writeObject(userinput); // Send
                String response = (String) in.readObject(); // Receive
                System.out.println("Receive :" + response);
            }
        } catch (Exception e) {
            System.out.println("P3: " + e.toString());
        } finally {
            try {
                if (s != null) s.close();
                scanner.close(); // Close the scanner
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.toString());
            }
        }
    }
}
