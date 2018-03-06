package exercise3;

import java.rmi.Naming;
import java.util.Scanner;

class Client {
	public static void main (String[] args) throws Exception {
		System.out.println("Starter klient...");
		String url = "rmi://localhost/Calculator";
		BasicCalculator bc = (BasicCalculator) Naming.lookup(url);
		Scanner sc = new Scanner(System.in);
		boolean noExit = true;
		
		while(noExit) {
			System.out.println("\n1 - add\n2 - subtract\n3 - exit");
			String res = sc.nextLine();
			String nr1 = "0", nr2 = "0";
			switch (res) {
				case "1":
					System.out.println("First number: ");
					nr1 = sc.nextLine();
					System.out.println("Second number: ");
					nr2 = sc.nextLine();
					System.out.println(bc.Calculation("add", nr1, nr2));
					break;
				case "2":
					System.out.println("First number: ");
					nr1 = sc.nextLine();
					System.out.println("Second number: ");
					nr2 = sc.nextLine();
					System.out.println(bc.Calculation("subtract", nr1, nr2));
					break;
				case "3":
					sc.close();
					noExit = false;
					break;
				default:
					System.out.println("...\n");
					break;
			}
		}
		System.exit(0);
	}
}
