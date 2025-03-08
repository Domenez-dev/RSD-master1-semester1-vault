import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class P2 { // Serveur

	public static void main(String[] args) {
		try {
			int port=2002;
			System.out.println("Created .... ");
			ServerSocket s = new ServerSocket(port);
			System.out.println("Waiting .....");
			Socket connection = s.accept();
			System.out.println("Accepted .....");
			
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
			String ch = (String) in.readObject();
			System.out.println("Message:"+ch);
			
			Socket c = new Socket("localhost", 2003);
			ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
			out.writeObject(ch+"OK");
			out.close();
			
			s.close();connection.close();in.close();		
			
		}
		catch(Exception e) {System.out.println("P1"+e.toString());	}

	}

}
