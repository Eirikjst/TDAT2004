package exercise3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class BasicCalculatorImplementation extends UnicastRemoteObject implements BasicCalculator {

	public BasicCalculatorImplementation() throws RemoteException {
	}

	private static final long serialVersionUID = 1L;
	
	public String Calculation(String operation, String input1, String input2) throws RemoteException {
		double nr1, nr2;
		try {
			nr1 = Double.parseDouble(input1);
			nr2 = Double.parseDouble(input2);
		} catch (NumberFormatException e) {
			return "Input is not a number";
		}
		if (operation == "add") return ("Answer is: "+Add(nr1, nr2));
		return ("Answer is: "+Subtract(nr1, nr2));
	}
	
	private String Add(double nr1, double nr2) {
		return String.format("%.2f", (nr1+nr2));
	}
	
	private String Subtract(double nr1, double nr2) {
		return String.format("%.2f", (nr1-nr2));
	}
}
