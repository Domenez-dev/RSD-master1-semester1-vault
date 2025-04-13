import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class P3 {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(2003); // Communication avec P2 - Pour Envoyer et recevoir
            Socket con = s.accept();
            ObjectInputStream in = new ObjectInputStream(con.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(
                con.getOutputStream()
            );

            // RMI connection to P4
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            IP4 iP4 = (IP4) registry.lookup("RefP4");

            while (true) {
                String ch = (String) in.readObject(); // receives from P2
                System.out.println("Receive from P2: " + ch);

                // Process and send to P4 via RMI
                ch = Integer.toString(Integer.parseInt(ch) * 3);
                String response = iP4.process(ch);

                System.out.println("Receive from P4: " + response);
                out.writeObject(response); // sends to P2
            }
        } catch (Exception e) {
            System.out.println("P3: " + e.toString());
        }
    }
}
