package exercise3;

import java.rmi.Naming;
import java.util.Scanner;

class Server {
	public static void main(String[] args) throws Exception {
		BasicCalculator bci = new BasicCalculatorImplementation();
		System.out.println("Starting server...");
		String name = "Calculator";
		Scanner sc = new Scanner(System.in);
		Naming.rebind(name, bci);
		
		System.out.println("Start server, url localhost/"+name);
		System.out.println("Type -2 to shutdown");
		String cmd = sc.nextLine();
		
		switch (cmd) {
			case "-1":
				System.out.println("Shutting down...");
				sc.close();
				Naming.unbind(name);
				break;
			default:
				System.out.println("...\n");
		}
		System.exit(0);
	}
}
