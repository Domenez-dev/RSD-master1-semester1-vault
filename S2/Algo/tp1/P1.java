import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class P1 { // client

	public static void main(String[] args) {
		try {
			
			Socket c = new Socket("192.168.172.206", 2002);
			System.out.println("Connected .....");
			
			ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
			out.writeObject("Master RSD");
			
			
			ObjectInputStream in = new ObjectInputStream(c.getInputStream());
			String ch = (String) in.readObject();
			System.out.println("Message:"+ch);
			c.close();in.close();out.close();
			
		}
		catch(Exception e) {System.out.println("P1"+e.toString());	}

	}

}
