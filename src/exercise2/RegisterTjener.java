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
		
		reg.regNyttUtstyr(1, "Utstyr 1", "Leverandør 1", 10, 1);
		reg.regNyttUtstyr(2, "Utstyr 2", "Leverandør 2", 5, 1);
		reg.regNyttUtstyr(3, "Utstyr 3", "Leverandør 3", 8, 1);
		reg.regNyttUtstyr(4, "Utstyr 4", "Leverandør 4", 15, 1);
		reg.regNyttUtstyr(5, "Utstyr 5", "Leverandør 5", 3, 1);
		
		System.out.println("Startet tjener under url localhost/"+objektNavn);
		System.out.println("Tast -1 for å avslutte");
		String cmd = sc.nextLine();
		
		switch (cmd) {
			case "-1":
				System.out.println("Avslutter");
				sc.close();
				Naming.unbind(objektNavn);
				System.exit(0);
				break;
			default:
				System.out.println("...\n");
		}
			
	}
}
