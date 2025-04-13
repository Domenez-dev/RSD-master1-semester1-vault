import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class P4 extends UnicastRemoteObject implements IP4 {

    public P4() throws Exception {}

    public String process(String data) throws Exception {
        System.out.println("Receive: " + data);
        String result = Integer.toString(Integer.parseInt(data.trim()) * 4);
        return result;
    }

    public static void main(String[] args) {
        try {
            P4 p4 = new P4();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RefP4", p4);
            System.out.println("P4 is ready");
        } catch (Exception e) {
            System.err.println("P4 exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
