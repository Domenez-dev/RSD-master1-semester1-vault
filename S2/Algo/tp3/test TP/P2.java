import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets; // For String to bytes[] need

public class P2 {
    public static void main(String[] args) {
		// Declare Sockets here
        try {
		    // Code here
        } catch (Exception e) {
            System.out.println("P2: " + e.toString());
        } finally {
            try {
                if (socket != null) socket.close();
                scanner.close();  // Close the scanner
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.toString());
            }
        }
}
