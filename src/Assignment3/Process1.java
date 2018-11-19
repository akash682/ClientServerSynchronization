package Assignment3;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Process1 {
	public static int loop1 = 1;
	public static int pre_loop1 = 30;

	public static void main(String[] args) {
		int port1 = 6000;
		int port2 = 7000;

		System.out.println("Process1");
		Receive1 rv = new Receive1(port1, port2);
		Thread th0 = new Thread(rv);
		th0.start();

	}
}

class Convert1 {
	// TO USE UDP DATAPACKET
	// Convert int[] to byte[]
	public static byte[] intsToBytes(int[] ints) {
		ByteBuffer bb = ByteBuffer.allocate(4 * 4);
		IntBuffer ib = bb.asIntBuffer();
		for (int i = 0; i < ints.length; i++) {
			ib.put(ints[i]);
		}
		return bb.array();
	}

	// Convert byte[] to int[]
	public static int[] bytesToInts(byte[] bytes) {
		int[] ints = new int[bytes.length / 4];
		ByteBuffer.wrap(bytes).asIntBuffer().get(ints);
		return ints;
	}
}

class Receive1 implements Runnable {
	private int port_rec;
	private int port_send;
	private int[] key;

	public Receive1(int port_rec, int port_send) {
		this.port_rec = port_rec;
		this.port_send = port_send;
	}

	@Override
	public void run() {

		try (DatagramSocket socket = new DatagramSocket(port_rec)) {
			while (true) {
				System.out.println("----------------------------\nLoop :" + Process1.loop1);
				// PREPARE UDP BUFFER TO RECIEVE
				byte[] buffer = new byte[4];
				DatagramPacket packet0 = new DatagramPacket(buffer, buffer.length);
				// RECEIVE
				System.out.println("WAITING FOR THE KEY...");
				socket.receive(packet0);

				// Convert byte[] to int[]
				Convert1 con = new Convert1();
				key = con.bytesToInts(buffer);
				System.out.println("Received key :" + key[0]);

				if (Process1.loop1 <= Process1.pre_loop1) {
					if (key[0] == 1111) {
						BufferedReader br = new BufferedReader(new FileReader(Process0.file));
						int counter_read = Integer.parseInt(br.readLine());
						br.close();

						counter_read++;
						if (counter_read >= Process0.pre_loop0 + Process1.pre_loop1 + Process2.pre_loop2) {
							System.out.println("MODIFIED VALUE TO :" + counter_read);
							String counter_write = String.valueOf(counter_read);
							BufferedWriter bw = new BufferedWriter(new FileWriter(Process0.file));
							bw.write(counter_write);
							bw.close();
							break;
						} else {
							System.out.println("MODIFIED VALUE TO :" + counter_read);
							String counter_write = String.valueOf(counter_read);
							BufferedWriter bw = new BufferedWriter(new FileWriter(Process0.file));
							bw.write(counter_write);
							bw.close();
						}

					} else {
						continue;
					}
				}

				try {
					// CONVERT INT[](PARAMETER) to BYTE[]
					byte[] arrb = con.intsToBytes(key);

					// OBTAIN LOCAL ADDRESS
					InetAddress address = InetAddress.getLocalHost();

					// UDP SOCKET
					DatagramSocket datagramSocket = new DatagramSocket();
					DatagramPacket packet1 = new DatagramPacket(arrb, arrb.length, address, port_send);

					// SEND
					datagramSocket.send(packet1);
					System.out.println("Key sent to Process2.");
					key = null;

					Process1.loop1++;

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		} catch (SocketException e) {
			System.out.println("SocketException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}

	}
}
