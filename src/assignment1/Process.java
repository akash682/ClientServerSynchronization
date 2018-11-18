/**
 * 
 */
package assignment1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import java.util.Comparator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author sohilshrestha Process
 */
public class Process {
	// Information
	private int PID;
	private int PORT;
	private String IPaddress;

	// Local Clock of the Process
	public static LamportLocalClock Ci;

	// Receiving Socket
	MulticastSocket multicastRecvSock;

	// Sending Socket
	MulticastSocket multicastSendSock;

	// Group of processes : All process registered to same group
	private InetAddress group;

	// Endlessly listen to the port to receive message
	Thread commthread;

	// Endlessly look at the receive message buffer/ send ACK or issue message
	Thread senderThread;

	// recvQueue, Messages received are buffered,
	public static PriorityBlockingQueue<Message> recvQueue;
	// Sort the recvQueue as per the Process Id
	Comparator<Message> comparator;

	public static BlockingQueue<String> ackQueue;

	public Process(int PID, int PORT, String IPaddress) throws IOException {
		this.PID = PID;
		this.PORT = PORT;
		this.IPaddress = IPaddress;

		Ci = new LamportLocalClock();

		group = InetAddress.getByName(IPaddress);

		multicastRecvSock = new MulticastSocket(PORT);

		multicastRecvSock.joinGroup(group);

		multicastSendSock = new MulticastSocket();

		commthread = new CommunicationThread(multicastRecvSock, PID);

		senderThread = new SenderThread(multicastRecvSock, group, PID, PORT);

		this.comparator = new MessageComparator();

		recvQueue = new PriorityBlockingQueue<Message>(100, comparator);

		ackQueue = new ArrayBlockingQueue<String>(100, true);
		System.out.println("Process " + PID + " with local clock Ci:" + Ci.getLocalClock() + " started");
	}

	public int getPID() {
		return PID;
	}

	public int getPORT() {
		return PORT;
	}

	public String getIPaddress() {
		return IPaddress;
	}

	public void startProcessThread() {
		commthread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		senderThread.start();
	}

}
//Sorting the queue based priority
class MessageComparator implements Comparator<Message> {
	@Override
	public int compare(Message a, Message b) {
		if (a.getLogicalClockValue() == b.getLogicalClockValue())
			return a.getSenderPID() - b.getSenderPID();
		return a.getLogicalClockValue() - b.getLogicalClockValue();
	}
}
