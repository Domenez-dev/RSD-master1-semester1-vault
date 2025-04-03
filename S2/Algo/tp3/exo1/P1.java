import java.io.*;
import java.net.*;
import java.util.Scanner;

public class P1 {

    public static void main(String[] args) {
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);
        try {
            // --- TCP Client Side ---
            socket = new Socket("localhost", 2002);
            ObjectOutputStream out = new ObjectOutputStream(
                socket.getOutputStream()
            );
            ObjectInputStream in = new ObjectInputStream(
                socket.getInputStream()
            );

            while (true) {
                // --- Read from keyboard ---
                System.out.print("Enter message: ");
                String userInput = scanner.nextLine();

                // Exit condition
                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }
                // --- Sending/Receiving TCP ---
                out.writeObject(userInput); // Send
                String response = (String) in.readObject(); // Receive
                System.out.println("Receive :" + response);
            }
        } catch (Exception e) {
            System.out.println("Client error: " + e.toString());
        } finally {
            try {
                if (socket != null) socket.close();
                scanner.close();
            } catch (IOException e) {
                System.out.println("Error closing sockets: " + e.toString());
            }
        }
    }
}
