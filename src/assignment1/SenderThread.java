package assignment1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * @author sohilshrestha Sender Thread is responsible for issuing message and
 *         sending updates as well as ACK
 */
public class SenderThread extends Thread {

	Logger logger = Logger.getLogger(CommunicationThread.class.getName());
	MulticastSocket multicastSock;
	int pid;
	int PORT;

	InetAddress group;

	private List<Integer> PORTAll;
	boolean messageIssued;
	
	boolean ackSent;//Flag fOR ACK sent for own message
	boolean delivered;//Flag fOR its own msg delivered

	public SenderThread(MulticastSocket multicastSock, InetAddress group, int pid, int port) {
		this.multicastSock = multicastSock;
		this.pid = pid;
		this.PORT = port;
		this.group = group;
		PORTAll = new ArrayList<Integer>();
		PORTAll.add(1234);
		PORTAll.add(1235);
		PORTAll.add(1236);

		messageIssued = false;
		ackSent = false;
		delivered= true;
	}

	public void run() {
		logger.info("PID:" + pid + " Sender Thread Ready");
		String msg = null;
		while (true) {

			try {
				// Issue Message
				if (messageIssued == false && delivered == true) {
					msg = IssueMessage();
					messageIssued = true;
					delivered = false;
					// MultiCast Message
					for (int port : PORTAll) {
						DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), group, port);
						multicastSock.send(packet);
					}
				}
				// Send ACK
				if (!Process.recvQueue.isEmpty()) {
					Message recvMessage= Process.recvQueue.take();
					if (recvMessage.getSenderPID() == pid && ackSent == false) {
						//Event Occur Increment by 1
						Process.Ci.incrementClock();
						messageIssued = false;
						msg = "ACK " + pid + " " + Process.Ci.getLocalClock();
						DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), group,
								recvMessage.getSenderPort());
						multicastSock.send(packet);
						ackSent = true;
					} else if (recvMessage.getSenderPID() != pid) {
						//Event Occur Increment by 1
						Process.Ci.incrementClock();
						msg = "ACK " + pid +" "+ Process.Ci.getLocalClock();
			
						DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), group,
								recvMessage.getSenderPort());
						multicastSock.send(packet);
						deliveryToApplication(recvMessage);

					}
				}
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void deliveryToApplication(Message m) {
		// TODO Auto-generated method stub

		try {
			
			System.out.println(
					pid + ": " + m.getSenderPID() + "." + m.getLogicalClockValue());
			delivered = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static char randomSeriesForThreeCharacter() {
		Random r = new Random();
		char random_Char = (char) (48 + r.nextInt(26));
		return random_Char;
	}

	private String IssueMessage() {
		// TODO Auto-generated method stub
		Process.Ci.incrementClock();
		return "MSG " + pid + " " + PORT + " " + randomSeriesForThreeCharacter() + " " + Process.Ci.getLocalClock();

	}

	private void sendACK(Message take) {
		// TODO Auto-generated method stub

	}

}
