package exercise4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
	
	private static byte[] convertToBytes(Object o) throws IOException {
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			out.close();
		}
		return out.toByteArray();
	}
	
	private static void sendAndReceive(DatagramSocket socket, DTO dto) throws IOException {
		byte[] buf = new byte[256];
		buf = convertToBytes(dto);
		InetAddress address = InetAddress.getByName("localhost");
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		socket.send(packet);
		
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String response = new String(packet.getData(), 0, packet.getLength());
		System.out.println("\nResult: "+response+"\n");
	}
	
	private static DTO setNumbers(DTO dto, Scanner sc) {
		System.out.println("First number:");
		dto.number1 = sc.nextDouble();
		System.out.println("Second number:");
		dto.number2 = sc.nextDouble();
		return dto;
	}
	
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		DTO dto = new DTO();
		
		Scanner sc = new Scanner(System.in);
		boolean noExit = true;
		
		while(noExit) {
			System.out.println("1 - add\n2 - subtract\n3 - multiply\n4 - divide\n5 - exit");
			int option = sc.nextInt();
			switch(option) {
				case 1:
					dto = setNumbers(dto, sc);
					dto.operation = "add";
					sendAndReceive(socket, dto);
					
					break;
				case 2:
					dto = setNumbers(dto, sc);
					dto.operation = "subtract";
					sendAndReceive(socket, dto);
					
					break;
				case 3:
					dto = setNumbers(dto, sc);
					dto.operation = "multiply";
					sendAndReceive(socket, dto);
					
					break;
				case 4:
					dto = setNumbers(dto, sc);
					dto.operation = "divide";
					sendAndReceive(socket, dto);
					
					break;
				case 5:
					System.out.println("Shutting down...");
					sc.close();
					socket.close();
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
