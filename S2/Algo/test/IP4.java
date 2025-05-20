import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IP4 extends Remote {
    int Add(int s2) throws Exception;
    void Affichage(String message) throws Exception;
}
