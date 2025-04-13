import java.rmi.registry.*;

public class P2 extends java.rmi.server.UnicastRemoteObject implements IP2 {

    public P2() throws Exception {
        super();
    }

    public int Add(int a, int b) throws Exception {
        return a + b;
    }

    public void Affichage(String message) throws Exception {
        System.out.println(message);
    }

    public static void main(String[] args) throws Exception {
        try {
            P2 p2 = new P2();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Refp", p2);
            System.out.println("P2 is ready");
        } catch (Exception e) {
            System.err.println("P2 exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
