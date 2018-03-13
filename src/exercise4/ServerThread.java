package exercise4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.*;

class ServerThread extends Thread {
	
	protected DatagramSocket socket = null;
	protected boolean shutdown = false;
	
	public ServerThread() throws IOException {
		socket = new DatagramSocket(4445);
	}
	
	public void shutdown(boolean exit) {
		shutdown = exit;
	}
	
	public void run() {
		while (!shutdown) {
			try {
				byte[] buf = new byte[256];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				DTO dto = (DTO)convertFromBytes(packet.getData());
				buf = calcOperation(dto).getBytes();
				
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
				shutdown = true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		socket.close();
	}
	
	private Object convertFromBytes(byte[] data) throws ClassNotFoundException, IOException {
		ObjectInput in = null;
		try{
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			in = new ObjectInputStream(bis);
		} catch (Exception e) {
			e.printStackTrace();
			in.close();
			return null;
		} finally {
			in.close();
		}
		return in.readObject();
	}
	
	private String calcOperation(DTO o) {
		String res = "";
		switch(o.operation) {
			case "add":
				res = new Calculator().add(o.number1, o.number2);
				break;
			case "subtract":
				res = new Calculator().subtract(o.number1, o.number2);
				break;
			case "multiply":
				res = new Calculator().multiply(o.number1, o.number2);
				break;
			case "divide":
				res = new Calculator().divide(o.number1, o.number2);
				break;
		}
		return res;
		
	}
}
