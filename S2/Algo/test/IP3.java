import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IP3 extends Remote {
    int Add(int s2, int n) throws Exception;
    void Affichage(String message) throws Exception;
    void SendToP4(String s3) throws Exception;
    void SendToP2(String x) throws Exception;
}
