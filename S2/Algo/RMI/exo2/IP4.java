import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IP4 extends Remote {
    String process(String data) throws Exception;
}
