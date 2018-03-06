package exercise3;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

class Server extends Thread implements ServerInterface {
	
	public static String result = "";
	
	public Server() {
	}
	
	public void test(String in) {
		System.out.println(in);
	}
	
	public String newClient(String operation, String nr1, String nr2) {
		ClientHandler ch = new ClientHandler(operation, nr1, nr2);
		Thread t = ch;
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			t.interrupt();
		}
		return ch.getResult();
	}
	
	public static void main(String[] args) {
		try {
			Server s = new Server();
			ServerInterface si = (ServerInterface) UnicastRemoteObject.exportObject(s, 0);
			
			Registry reg = LocateRegistry.getRegistry();
			String url = "calculator";
			reg.bind(url, si);
			
			System.out.println("Server started");
			System.out.println("Type -1 to shut down server...");
			Scanner sc = new Scanner(System.in);
			
			boolean noExit = true;
			while (noExit) {
				String cmd = sc.nextLine();
				switch (cmd) {
					case "-1":
						System.out.println("Shutting down...");
						sc.close();
						noExit = false;
						reg.unbind(url);
						break;
					default:
						System.out.println("...\n");
						break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Server exception "+e.toString());
			e.printStackTrace();
		}
		System.exit(0);
	}
}
