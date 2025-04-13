import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class P2 {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(2002); // Communication avec P1 - Pour recevoir
            Socket con = s.accept();
            ObjectInputStream in = new ObjectInputStream(con.getInputStream());

            DatagramSocket c = new DatagramSocket(); // Communication avec P1 - Pour envoyer

            Socket c1 = new Socket("localhost", 2003); // Communication avec P3 - Pour Envoyer et recevoir
            ObjectOutputStream out1 = new ObjectOutputStream(
                c1.getOutputStream()
            );
            ObjectInputStream in1 = new ObjectInputStream(c1.getInputStream());
            DatagramPacket p = null;

            while (true) {
                String ch = (String) in.readObject(); // Receives from P1
                System.out.println("Receive :" + ch);
                out1.writeObject(Integer.toString((Integer.parseInt(ch) * 2))); // Sends to P3
                ch = (String) in1.readObject(); // Receives from P3
                System.out.println("Receive :" + ch);

                p = new DatagramPacket(
                    ch.getBytes(),
                    ch.getBytes().length,
                    InetAddress.getByName("localhost"),
                    9875
                );
                c.send(p); // sends to P1
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
    }
}
