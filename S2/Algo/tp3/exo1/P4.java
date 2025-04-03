import java.io.*;
import java.net.*;

public class P4 {

    public static void main(String[] args) {
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            // --- TCP Server Side ---
            serverSocket = new ServerSocket(2004);
            Socket con = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(con.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(
                con.getOutputStream()
            );

            while (true) {
                // --- Sending/Receiving TCP ---
                String response = (String) in.readObject(); // Receive
                System.out.println("Receive :" + response);
                out.writeObject("World!" + response); // Send
            }
        } catch (Exception e) {
            System.out.println("Client error: " + e.toString());
        } finally {
            try {
                if (socket != null) socket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing sockets: " + e.toString());
            }
        }
    }
}
