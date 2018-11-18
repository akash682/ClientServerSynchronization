package Assignment2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Arrays;

public class Send implements Runnable {
	// DEFINE
	private int[] arr = new int[4];
	private int port1;
	private int port2;
	private int p_id;

	// INITIALIZATION , PARAMETER
	public Send(int port1, int port2, int p_id) {
		this.port1 = port1;
		this.port2 = port2;
		this.p_id = p_id;
	}

	// THREAD RUN
	@Override
	public void run() {
		try {
			// LOCAL EVENT
			if (p_id == 0) {
				while (true) {
					if (Device1.inuse_vec0 == true) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						Device1.inuse_vec0 = true;
						Device1.vec0[p_id] += 1;
						System.out.println("---------------------------\nLocal Event Occured!");
						System.out.println(
								"Current Vector :" + Arrays.toString(Device1.vec0) + "\n---------------------------");
						arr = Device1.vec0;
						Device1.inuse_vec0 = false;
						// CONVERT INT[](PARAMETER) to BYTE[]
						Convert con = new Convert();
						byte[] arrb = con.intsToBytes(arr);

						// OBTAIN LOCAL ADDRESS
						InetAddress address = InetAddress.getLocalHost();

						// UDP SOCKET
						DatagramSocket datagramSocket = new DatagramSocket();
						DatagramPacket packet1 = new DatagramPacket(arrb, arrb.length, address, port1);
						DatagramPacket packet2 = new DatagramPacket(arrb, arrb.length, address, port2);

						// SEND
						datagramSocket.send(packet1);
						datagramSocket.send(packet2);

						break;
					}
				}
			} else if (p_id == 1) {
				while (true) {
					if (Device2.inuse_vec1 == true) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						Device2.inuse_vec1 = true;
						Device2.vec1[p_id] += 1;
						System.out.println("---------------------------\nLocal Event Occured!");
						System.out.println(
								"Current Vector :" + Arrays.toString(Device2.vec1) + "\n------------------------");
						arr = Device2.vec1;
						Device2.inuse_vec1 = false;
						// CONVERT INT[](PARAMETER) to BYTE[]
						Convert con = new Convert();
						byte[] arrb = con.intsToBytes(arr);

						// OBTAIN LOCAL ADDRESS
						InetAddress address = InetAddress.getLocalHost();

						// UDP SOCKET
						DatagramSocket datagramSocket = new DatagramSocket();
						DatagramPacket packet1 = new DatagramPacket(arrb, arrb.length, address, port1);
						DatagramPacket packet2 = new DatagramPacket(arrb, arrb.length, address, port2);

						// SEND
						datagramSocket.send(packet1);
						datagramSocket.send(packet2);

						break;
					}

				}

			} else if (p_id == 2) {
				while (true) {
					if (Device3.inuse_vec2 == true) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						Device3.inuse_vec2 = true;
						Device3.vec2[p_id] += 1;
						System.out.println("---------------------------\nLocal Event Occured!");
						System.out.println(
								"Current Vector :" + Arrays.toString(Device3.vec2) + "\n------------------------");
						arr = Device3.vec2;
						Device3.inuse_vec2 = false;
						// CONVERT INT[](PARAMETER) to BYTE[]
						Convert con = new Convert();
						byte[] arrb = con.intsToBytes(arr);

						// OBTAIN LOCAL ADDRESS
						InetAddress address = InetAddress.getLocalHost();

						// UDP SOCKET
						DatagramSocket datagramSocket = new DatagramSocket();
						DatagramPacket packet1 = new DatagramPacket(arrb, arrb.length, address, port1);
						DatagramPacket packet2 = new DatagramPacket(arrb, arrb.length, address, port2);

						// SEND
						datagramSocket.send(packet1);
						datagramSocket.send(packet2);

						break;
					}
				}

			}

		} catch (SocketTimeoutException e) {
			System.out.println("Socket had timed out.");
		} catch (IOException e) {
			System.out.println("Client error: " + e.getMessage());
		}
	}
}