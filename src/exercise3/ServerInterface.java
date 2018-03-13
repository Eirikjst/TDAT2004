package exercise3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	void test(String in) throws RemoteException;
	
	String newClient(String operation, String nr1, String nr2) throws RemoteException;
}
