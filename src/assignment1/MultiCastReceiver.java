package assignment1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiCastReceiver extends Thread {
	private final static int PORT = 1234;

	public static void main(String[] args) {
		try {
			InetAddress group = InetAddress.getByName("224.0.0.1");
			MulticastSocket multicastSock = new MulticastSocket(PORT);
			multicastSock.joinGroup(group);

			byte[] buffer = new byte[100];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			while (true) {
				System.out.println("Receiving");
				multicastSock.receive(packet);

				System.out.println(buffer.toString());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
