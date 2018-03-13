package exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class WebSocket {
	public static void main(String[] args) throws IOException {
		
		ServerSocket server = new ServerSocket(80, 0, InetAddress.getLoopbackAddress());
	    System.out.println("Server has started on localhost:80.\r\nWaiting for a connection...");
	    Socket socket = server.accept();
	    String header = "<html><body><h1>Hi, You have connected to a webpage</h1><ul>";
	    
	    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        for (String line; (line = reader.readLine()) != null;) {
        	if (line.isEmpty()) break;
        	header += "<li>"+line+"</li>";
        }
        header += "</ul></body></html>";
        OutputStream out = socket.getOutputStream();
        out.write(header.getBytes());
        out.flush();
        System.out.println("Shutting down...");
	}
}
