package A5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class P3 {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(2003); // Communication avec P2 - Pour Envoyer et recevoir
            Socket con = s.accept();
            ObjectInputStream in = new ObjectInputStream(con.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(
                con.getOutputStream()
            );

            DatagramSocket c = new DatagramSocket(); // Communication avec P4 - Pour Envoyer et recevoir
            byte[] receiveT = new byte[20];
            InetAddress ip = InetAddress.getByName("localhost");
            DatagramPacket p = null;

            while (true) {
                String ch = (String) in.readObject(); // recieves from P2
                System.out.println("Receive :" + ch);
                ch = Integer.toString(Integer.parseInt(ch) * 3);
                p = new DatagramPacket(
                    ch.getBytes(),
                    ch.getBytes().length,
                    ip,
                    9876
                );
                c.send(p); // Sends to P4

                p = new DatagramPacket(receiveT, receiveT.length);
                c.receive(p); // recieves from P4
                System.out.println(
                    "Receive :" + new String(p.getData()).trim()
                );
                out.writeObject(new String(p.getData()).trim()); // sends to P2
            }
        } catch (Exception e) {
            System.out.println("P3: " + e.toString());
        }
    }
}
