import java.rmi.Remote;

public interface IP2 extends Remote {
    int Add(int a, int b) throws Exception;
    void Affichage(String message) throws Exception;
}
