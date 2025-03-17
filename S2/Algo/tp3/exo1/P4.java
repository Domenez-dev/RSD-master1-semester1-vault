import java.io.*;
import java.net.*;

public class P4 {
    public static void main(String[] args) {
        // TCP P1-P2
        try {
            ServerSocket serverSocket = new ServerSocket(7000);
            System.out.println("waiting for P3...");

            while (true) {
                Socket socket = serverSocket.accept();
                    System.out.println("P3 connected");

                    // Receive message from P3
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = in.readLine();
                    System.out.println("Received from P1: " + message);

                    // Modify the message
                    String modifiedMessage = Integer.toString(Integer.parseInt(message) * 2);

                    // Send back to P3
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(modifiedMessage);
                }
        } catch(Exception e) {System.out.println("P1"+e.toString());}
    }
}
