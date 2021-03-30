import java.net.*;
import java.io.*;

public class Client {
	private static InetAddress hostAddress;
	private	static int serverPort = 6789;
	private static String msg = "Ping";
	private static DatagramSocket datagramSocket;

	public static void main(String args[]) {
		try {
			hostAddress = InetAddress.getByName("localhost");
			datagramSocket = new DatagramSocket();
			while (true) {
				sendMessage();
				getResponse();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datagramSocket.close();
		}
	}

	private static void sendMessage() throws IOException, InterruptedException {
			Thread.sleep(1000);
			byte[] m = msg.getBytes();
			DatagramPacket request = new DatagramPacket(m, msg.length(),
					hostAddress, serverPort);
			datagramSocket.send(request);
	}

	private static void getResponse() throws IOException {
		byte[] buffer = new byte[1000];
		DatagramPacket response = new DatagramPacket(buffer, buffer.length);
		datagramSocket.receive(response);
		String resp = new String(response.getData());
		System.out.println(resp);
	}
}