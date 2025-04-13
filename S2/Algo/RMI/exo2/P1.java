import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class P1 {

    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(9875); // Communication avec P2 - Pour recevoir
            byte[] receiveT = new byte[20];
            DatagramPacket q = new DatagramPacket(receiveT, receiveT.length);

            Socket c = new Socket("localhost", 2002); // Communication avec P2 - Pour envoyer
            ObjectOutputStream out = new ObjectOutputStream(
                c.getOutputStream()
            );

            while (true) {
                out.writeObject("10"); // Sends to P2
                s.receive(q); // Receives from P2
                String str = new String(q.getData());
                System.out.println("Receive: " + str);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
    }
}
