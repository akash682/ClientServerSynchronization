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

	private static BlockingQueue<String> sendClockReplyQueue;

	public CommunicationThread(MulticastSocket multicastSock, int pid) {
		this.multicastSock = multicastSock;
		this.pid = pid;

	}

	public void run() {
		// logger.info("PID:" + pid + " Communication Receive Thread Ready");
		while (true) {

			byte[] buffer = new byte[100];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			try {
				multicastSock.receive(packet);
				String receivedMessage = new String(buffer, 0, packet.getLength());
				// logger.info("Received Message by PID"+ pid + ":"+receivedMessage);
				String[] splitM = receivedMessage.split(" ");
				Message recM = null;
				if (splitM[0].compareTo("MSG") == 0) {
					recM = new Message(Integer.parseInt(splitM[1]), Integer.parseInt(splitM[2]), splitM[3],
							Integer.parseInt(splitM[4]));
					Process.Ci.compareTimeStamp(Integer.parseInt(splitM[4]));
					Process.recvQueue.put(recM);
				} else if (splitM[0].compareTo("ACK") == 0 && !Process.recvQueue.isEmpty()
						&& Process.recvQueue.peek().getSenderPID() == pid) {// We need 3 ACK to deliver our own message
					try {
						Process.ackQueue.put(splitM[1]);
						Process.Ci.compareTimeStamp(Integer.parseInt(splitM[2]));
						Message m = null;
						logger.info("Size of ACK Queue : " + String.valueOf(Process.ackQueue.size()));

//						if (!Process.recvQueue.isEmpty()) {
//							logger.info(" receive Queue content head: " + Process.recvQueue.peek().getSenderPID() + "."
//									+ Process.recvQueue.peek().getLogicalClockValue());
//							logger.info("1?" + String.valueOf(Process.ackQueue.contains("1")) + " " + "2?"
//									+ String.valueOf(Process.ackQueue.contains("2")) + "3?"
//									+ String.valueOf(Process.ackQueue.contains("3")));
//						}

						// If ACK is for my own issued message , deliver message
						if (!Process.recvQueue.isEmpty() && Process.recvQueue.peek().getSenderPID() == pid) {
							logger.info("1?" + String.valueOf(Process.ackQueue.contains("1")) + " " + "2?"
									+ String.valueOf(Process.ackQueue.contains("2")) + "3?"
									+ String.valueOf(Process.ackQueue.contains("3")));
							if (Process.ackQueue.size() == 3) {//deliver message if 3 ack received
								try {
									m = Process.recvQueue.take();
								
									System.out.println(pid + ": " + m.getSenderPID() + "." + m.getLogicalClockValue());
									Process.ackQueue.clear();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
