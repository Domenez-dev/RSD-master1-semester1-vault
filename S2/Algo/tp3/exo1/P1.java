import java.io.*;
import java.net.*;
import java.util.Scanner;

public class P1 {

    public static void main(String[] args) {
        Socket s = null;
        Scanner scanner = new Scanner(System.in);
        try {
            // --- Client Side ---
            Socket s = new Socket("localhost", 2002);
            ObjectOutputStream out = new ObjectOutputStream(
                s.getOutputStream()
            );
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());

            while (true) {
                // --- Read from keyboard ---
                System.out.print("Enter message: ");
                String userInput = scanner.nextLine();
                out.writeObject(userInput);
            }
        } catch (Exception e) {
            System.out.println("Client error: " + e.toString());
        } finally {
            try {
                if (s != null) s.close();
                scanner.close();
            } catch (IOException e) {
                System.out.println("Error closing sockets: " + e.toString());
            }
        }
    }
}
