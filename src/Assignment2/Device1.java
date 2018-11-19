package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Device1 {
	// GLOBAL LIST TO STORE RECEIVED VECTOR
	public static volatile List<int[]> myList0 = new ArrayList<int[]>();
	public static int[] vec0 = new int[4];
	public static boolean inuse_list0 = false;
	public static boolean inuse_vec0 = false;

	public static void main(String[] args) {
		// INITIALIZATION
		for (int i = 0; i < vec0.length - 1; i++) {
			vec0[i] = 0;
		}
		int p_id = 0;
		vec0[vec0.length - 1] = p_id;
		int port0 = 5000;
		int port1 = 6000;
		int port2 = 7000;
		int[] vec0_result = new int[3];

		// INITIALVECTOR PRINT
		System.out.println("**VECTER = [TIMESTAMP_DEVICE1, TIMESTAMP_DEVICE2, TIMESTAMP_DEVICE3, DEVICE_ID]**");
		System.out.println("Initial Vector :" + Arrays.toString(vec0) + "\n---------------------------");

		// RECEIVE & ADDLIST
		Recieve rec = new Recieve(port0, p_id);
		Thread th0 = new Thread(rec);
		th0.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// CHOOSE & MODIFY
		Choose ch = new Choose(p_id);
		Thread th2 = new Thread(ch);
		th2.start();

		// SEND 0 -> 1
		Send send01 = new Send(port1, port2, p_id);
		Thread th3 = new Thread(send01);
		th3.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < vec0_result.length; i++) {
			vec0_result[i] = vec0[0];
		}

		System.out.println("Vector_result :" + Arrays.toString(vec0_result));

		th0.stop();
		th2.stop();


	}

}
