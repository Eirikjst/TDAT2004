package exercise2;

import java.rmi.Naming;
import java.util.Scanner;

class RegisterKlient {
	public static void main(String[] args) throws Exception {
		System.out.println("Starter klient...");
		String url = "rmi://localhost/register";
		Register reg = (Register) Naming.lookup(url);
		Scanner sc = new Scanner(System.in);
		boolean noExit = true;
		while (noExit) {
			System.out.println("1 - Databeskrivelse\n2 - Bestillingsliste\n3 - endre lagerbeholdning\n4 - avslutt");
			String in = sc.nextLine();
			switch (in) {
				case "1":
					System.out.println(reg.lagDatabeskrivelse());
					break;
				case "2":
					System.out.println(reg.lagBestillingsliste());
					break;
				case "3":
					System.out.println("Utstyr nr: ");
					int nr = sc.nextInt();
					System.out.println("Mengde: ");
					int mengde = sc.nextInt();
					reg.endreLagerbeholdning(nr, mengde);
					break;
				case "4":
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
