package exercise3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class BasicCalculatorImplementation extends UnicastRemoteObject implements BasicCalculator {

	public BasicCalculatorImplementation() throws RemoteException {
	}

	private static final long serialVersionUID = 1L;
	
	public String calculation(String operation, String input1, String input2) throws RemoteException {
		double nr1, nr2;
		try {
			nr1 = Double.parseDouble(input1);
			nr2 = Double.parseDouble(input2);
		} catch (NumberFormatException e) {
			return "Input is not a number";
		}
		if (operation.equals("add")) return ("Answer is: "+add(nr1, nr2));
		else return ("Answer is: "+subtract(nr1, nr2));
	}
	
	private String add(double nr1, double nr2) {
		return String.format("%.2f", (nr1 + nr2));
	}
	
	private String subtract(double nr1, double nr2) {
		return String.format("%.2f", (nr1 - nr2));
	}
}
