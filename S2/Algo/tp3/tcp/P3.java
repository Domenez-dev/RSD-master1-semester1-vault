import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class P3 { // client

	public static void main(String[] args) {
		try {
			System.out.println("Created .... ");
			ServerSocket s = new ServerSocket(2003, 10);
			System.out.println("Waiting .....");
			Socket connection = s.accept();
			System.out.println("Accepted .....");
			
			ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
			String ch = (String) in.readObject();
			System.out.println("Message:"+ch);
			s.close();connection.close();in.close();
		
			
			
		}
		catch(Exception e) {System.out.println("P1"+e.toString());	}

	}

}
