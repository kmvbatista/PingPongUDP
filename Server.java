import java.net.*;
import java.io.*;

public class Server {
	private static String responseMessage = "Pong";
	private static DatagramSocket aSocket;
	
	public static void main(String args[]) {
		try {
			aSocket = new DatagramSocket(6789);
			byte[] buffer = new byte[1000];
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			while (true) {
				getMessage(request, buffer);
				sendMessage(request, aSocket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			aSocket.close();
		}
	}

	private static void getMessage(DatagramPacket request, byte[] buffer) throws IOException {
		aSocket.receive(request);
		System.out.println(new String(buffer));
	}

	private static void sendMessage(DatagramPacket request, DatagramSocket aSocket) throws IOException, InterruptedException {
		Thread.sleep(1000);
		byte[] m = responseMessage.getBytes();
		DatagramPacket response = new DatagramPacket(m, responseMessage.length(),
				request.getAddress(), request.getPort());
		aSocket.send(response);
	}
}