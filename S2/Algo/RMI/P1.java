import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class P1 {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            IP2 i = (IP2) registry.lookup("Refp");
            int x = i.Add(1, 2);
            System.out.println("Result: " + x);
            i.Affichage("Hello");
        } catch (Exception e) {
            System.err.println("Registry lookup failed: " + e.toString());
        }
    }
}
