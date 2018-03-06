package exercise3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

class Client {
	public static void main (String[] args) throws Exception {
		String host = "calculator";
		Scanner sc = new Scanner(System.in);
		boolean noExit = true;
		try {
			System.out.println("Starting client...");
			Registry reg = LocateRegistry.getRegistry();
			ServerInterface server = (ServerInterface) reg.lookup(host);
			while (noExit) {
				System.out.println("1 - Add\n2 - Subtract\n3 - Exit");
				String cmd = sc.nextLine();
				String nr1 = "0", nr2 = "0";
				switch (cmd) {
					case "1":
						System.out.println("First number: ");
						nr1 = sc.nextLine();
						System.out.println("Second number: ");
						nr2 = sc.nextLine();
						System.out.println(server.newClient("add", nr1, nr2));
						break;
					case "2":
						System.out.println("First number: ");
						nr1 = sc.nextLine();
						System.out.println("Second number: ");
						nr2 = sc.nextLine();
						System.out.println(server.newClient("subtract", nr1, nr2));
						break;
					case "3":
						System.out.println("Shutting down...");
						sc.close();
						noExit = false;
						break;
					default:
						System.out.println("...");
						break;
				}
			}
		} catch (Exception e) {
			System.out.println("Client error: "+e.toString());
			e.printStackTrace();
		}
		System.exit(0);
	}
}
