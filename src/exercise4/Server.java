package exercise4;

import java.io.IOException;
import java.util.Scanner;

class Server {
	public static void main(String[] args) throws IOException {
		System.out.println("Starting server");
		ServerThread server = new ServerThread();
		server.start();
		System.out.println("Server Started\nType -1 to shutdown");
		
		Scanner sc = new Scanner(System.in);
		boolean noExit = true;
		
		while(noExit) {
			String cmd = sc.nextLine();
			switch (cmd) {
				case "-1":
					System.out.println("Shutting down...");
					sc.close();
					server.shutdown(true);
					noExit = false;
					break;
				default:
					System.out.println("...");
					break;
			}
		}
		System.exit(0);
		
	}
}
