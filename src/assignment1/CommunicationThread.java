package assignment1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * @author sohilshrestha Create a Datagram socket that will listen to a PORT
 * 
 */
public class CommunicationThread extends Thread {

	Logger logger = Logger.getLogger(CommunicationThread.class.getName());
	MulticastSocket multicastSock;
	int pid;

	// Keep Track of ACK of its own Issued Message
	int ACKrev;

	private static BlockingQueue<String> sendClockReplyQueue;

	public CommunicationThread(MulticastSocket multicastSock, int pid) {
		this.multicastSock = multicastSock;
		this.pid = pid;

		ACKrev = -1; // Maintains number of ACK received for the issued message

	}

	public int getACKrev() {
		return ACKrev;
	}

	public void setACKrev(int aCKrev) {
		ACKrev = aCKrev;
	}

	public void run() {
		logger.info("PID:" + pid + " Communication Receive Thread Ready");
		while (true) {

			byte[] buffer = new byte[100];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			try {
				multicastSock.receive(packet);
				String receivedMessage = new String(buffer, 0, packet.getLength());
				logger.info("Received Message by PID"+ pid + ":"+receivedMessage);
				String[] splitM = receivedMessage.split(" ");
				Message recM = null;
				if (splitM[0].compareTo("MSG") == 0) {
					recM = new Message(Integer.parseInt(splitM[1]), Integer.parseInt(splitM[2]), splitM[3],
							Integer.parseInt(splitM[4]));
					Process.Ci.compareTimeStamp(Integer.parseInt(splitM[4]));
					Process.recvQueue.put(recM);
				} else if (splitM[0].compareTo("ACK") == 0) {
					try {
						Process.ackQueue.put(splitM[1]);
						Message m = null;
						// If ACK is for my own issued message
						if (!Process.recvQueue.isEmpty() && Process.recvQueue.peek().getSenderPID() == pid)
							m = Process.recvQueue.take();
						if (m==null)
							continue;
						logger.info("Size of Receive Queue : " + String.valueOf(Process.recvQueue.size()));
						deliveryToApplication(m);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// System.out.println(Process.recvQueue.size());
				// System.out.println(Process.recvQueue.peek().getData());

				/*
				 * // Send ACK for the first message on the Queue. if
				 * (!Process.recvQueue.isEmpty()) { try { sendACK(Process.recvQueue.take()); }
				 * catch (InterruptedException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } logger.info(receivedMessage); }
				 */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void deliveryToApplication(Message m) {
		// TODO Auto-generated method stub
		logger.info("Size of ACKQUE of PID"+String.valueOf(pid)+" :"+String.valueOf(Process.ackQueue.size()));
		if (!Process.recvQueue.isEmpty())
			if (Process.ackQueue.size() == 3) {
				try {
		
					System.out.println(pid + ": " + m.getSenderPID() + "."
							+ m.getLogicalClockValue());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}

}
