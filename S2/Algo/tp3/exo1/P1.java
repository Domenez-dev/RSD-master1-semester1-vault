import java.io.*;
import java.net.*;

public class P1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to P2");
        
                        // send
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("15");

                        // Received
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            System.out.println("Received from P2: " + response);
        } catch(Exception e) {System.out.println("P1"+e.toString());}
    }
}
