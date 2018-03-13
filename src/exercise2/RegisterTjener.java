package exercise2;

import java.rmi.Naming;
import java.util.Scanner;

class RegisterTjener {
	public static void main(String[] args) throws Exception {
		System.out.println("Starter tjener");
		String objektNavn = "register";
		Scanner sc = new Scanner(System.in);
		Register reg = new RegisterImpl();
		Naming.rebind(objektNavn, reg);
		boolean noExit = true;
		
		reg.regNyttUtstyr(1, "Utstyr 1", "Leverandor 1", 10, 1);
		reg.regNyttUtstyr(2, "Utstyr 2", "Leverandor 2", 5, 1);
		reg.regNyttUtstyr(3, "Utstyr 3", "Leverandor 3", 8, 1);
		reg.regNyttUtstyr(4, "Utstyr 4", "Leverandor 4", 15, 1);
		reg.regNyttUtstyr(5, "Utstyr 5", "Leverandor 5", 3, 1);
		
		System.out.println("localhost/"+objektNavn);
		System.out.println("Tast -1 for a avslutte");
		while (noExit) {
			String cmd = sc.nextLine();
			switch (cmd) {
				case "-1":
					System.out.println("Avslutter");
					sc.close();
					noExit = false;
					Naming.unbind(objektNavn);
					break;
				default:
					System.out.println("...");
					break;
			}	
		}
		System.exit(0);
	}
}
