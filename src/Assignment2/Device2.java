package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Device2 {
	// GLOBAL LIST TO STORE RECEIVED VECTOR
	public static volatile List<int[]> myList1 = new ArrayList<int[]>();
	public static int[] vec1 = new int[4];
	public static boolean inuse_list1 = false;
	public static boolean inuse_vec1 = false;

	public static void main(String[] args) {
		// INITIALIZATION
		for (int i = 0; i < vec1.length - 1; i++) {
			vec1[i] = 0;
		}
		int p_id = 1;
		vec1[vec1.length - 1] = p_id;
		int port0 = 5000;
		int port1 = 6000;
		int port2 = 7000;
		int[] vec1_result = new int[3];

		// INITIALVECTOR PRINT
		System.out.println("**VECTER = [TIMESTAMP_DEVICE1, TIMESTAMP_DEVICE2, TIMESTAMP_DEVICE3, DEVICE_ID]**");
		System.out.println("Initial Vector :" + Arrays.toString(vec1) + "\n---------------------------");

		// RECEIVE & ADDLIST
		Recieve rec = new Recieve(port1, p_id);
		Thread th0 = new Thread(rec);
		th0.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// CHOOSE & MODIFY
		Choose ch = new Choose(p_id);
		Thread th2 = new Thread(ch);
		th2.start();

		// SEND 1 -> 0
		Send send10 = new Send(port0, port2, p_id);
		Thread th3 = new Thread(send10);
		th3.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < vec1_result.length; i++) {
			vec1_result[i] = vec1[i];
		}

		System.out.println("Vector_result :" + Arrays.toString(vec1_result));

		th0.stop();
		th2.stop();

		System.exit(0);

	}

}