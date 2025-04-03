package A5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class P4 {

	public static void main(String[] args) {
		try {			
			DatagramSocket s = new DatagramSocket(9876); // Communication avec P3 - Pour Envoyer et recevoir
			byte[] receiveT = new byte[20];
			DatagramPacket q = new DatagramPacket(receiveT, receiveT.length);
			DatagramPacket r=null;
			
			while (true) {
			s.receive(q); // recieves from P3
			String str = new String(q.getData());
			System.out.println("Receive :"+str);	
			str=Integer.toString(Integer.parseInt(str.trim())*4);
			r = new DatagramPacket(str.getBytes(), str.getBytes().length, q.getAddress(), q.getPort());
			s.send(r);  // sends to P3
			}
			}catch(Exception e){System.out.println("P1: "+e.toString());}	

	}

}
