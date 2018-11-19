package assignment1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
	public static void main(String[] args) {
		try {
			InetAddress group = InetAddress.getByName("224.0.0.1");
			MulticastSocket multicastSock = new MulticastSocket();

			int senderPID = 1;
			int senderPort = 2345;
			String data = "m";
			int logicalClockValue = 0;
			String msg = "MSG " + senderPID + " " + senderPort + " " + data + " " + logicalClockValue;
			//String msg1 = "MSG " + senderPID + " " + eventId + " END";
			String msg2 = "MSG " + 2 + " " + senderPort + " " + "n" + " " + logicalClockValue;
			Message m = new Message(senderPID, senderPort, data, logicalClockValue);
	
			byte[] buffer = new byte[100];
			DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), group, 1234);
			
			DatagramPacket packet2 = new DatagramPacket(msg2.getBytes(), msg2.length(), group, 1234);


			for (int i = 1;i<9;i++)
				multicastSock.send(packet);

			multicastSock.send(packet2);
			multicastSock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
