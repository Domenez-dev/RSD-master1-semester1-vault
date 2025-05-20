import java.rmi.registry.*;

public class P4 extends java.rmi.server.UnicastRemoteObject implements IP4 {

    public P4() throws Exception {
        super();
    }

    public int Add(int s3) throws Exception {
        return (s3 * 4);
    }

    public void Affichage(String message) throws Exception {
        System.out.println(message);

        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1097);
        IP3 i = (IP3) registry.lookup("Refp");
        i.SendToP2(message);
    }

    public static void main(String[] args) throws Exception {
        try {
            P3 p3 = new P3();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Refp", p3);
            System.out.println("P3 is ready");
        } catch (Exception e) {
            System.err.println("P3 exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
