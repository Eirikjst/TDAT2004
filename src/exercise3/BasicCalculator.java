package exercise3;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface BasicCalculator extends Remote {
	String Calculation(String operation, String input1, String input2) throws RemoteException;
}
