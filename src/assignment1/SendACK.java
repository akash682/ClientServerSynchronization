/**
 * 
 */
package assignment1;

import java.net.MulticastSocket;

/**
 * @author sohilshrestha
 *Sorting Thread 
 */
public class SendACK extends Thread{
	MulticastSocket multicastSock;
	int pid;

	public SendACK(MulticastSocket multicastSock, int pid) {
		this.multicastSock = multicastSock;
		this.pid = pid;
	}
	 
	public void run() {
		System.out.println(pid+ "Sort Receive Thread Ready");
		while (true) {

			
		}
	}
}
