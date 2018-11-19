package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Device3 {
	// GLOBAL LIST TO STORE RECEIVED VECTOR
	public static volatile List<int[]> myList2 = new ArrayList<int[]>();
	public static int[] vec2 = new int[4];
	public static boolean inuse_list2 = false;
	public static boolean inuse_vec2 = false;

	public static void main(String[] args) {
		// INITIALIZATION
		for (int i = 0; i < vec2.length - 1; i++) {
			vec2[i] = 0;
		}
		int p_id = 2;
		vec2[vec2.length - 1] = p_id;
		int port0 = 5000;
		int port1 = 6000;
		int port2 = 7000;
		int[] vec2_result = new int[3];

		// INITIALVECTOR PRINT
		System.out.println("**VECTER = [TIMESTAMP_DEVICE1, TIMESTAMP_DEVICE2, TIMESTAMP_DEVICE3, DEVICE_ID]**");
		System.out.println("Initial Vector :" + Arrays.toString(vec2) + "\n---------------------------");

		// RECEIVE & ADDLIST
		Recieve rec = new Recieve(port2, p_id);
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

		// SEND 2 -> 0

		Send send20 = new Send(port0, port1, p_id);
		Thread th3 = new Thread(send20);
		th3.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < vec2_result.length; i++) {
			vec2_result[i] = vec2[1];
		}

		System.out.println("Vector_result :" + Arrays.toString(vec2_result));
		th0.stop();
		th2.stop();


	}

}
