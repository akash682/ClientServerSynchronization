package deef;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;


public class Device1 {
    //GLOBAL LIST TO STORE RECEIVED VECTOR
    public static List<int[]> myList = new ArrayList<int[]>();

    public static void main(String[] args) {
        //DEFINE
        int vec1[] = new int[3];
        int i;


        //INITIALIZATION
        for (i = 0; i < vec1.length; i++) {
            vec1[i] = 5 * i;
        }
        int port1 = 5000;

        //TEST PRINT by Thread LIST(STORING RECEIVED ARRAY)
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Inside Thread...");
                    if (myList.size() > 0) {
                        for (int[] arr : myList) {
                            for (int i : arr) {
                                System.out.print(i + ",");
                            }
                            System.out.println("\n");
                        }
                    }
                }
            }
        })).start();
        System.out.println("Outside thread...");

        //RECEIVE & ADDLIST
        try (DatagramSocket socket = new DatagramSocket(port1)) {
            System.out.println("Running Receive Thread");
            //RECEIVE THREAD RUN
            while(true) {
                Recieve rec = new Recieve(socket);
                rec.run();
            }
        } catch (IOException e) {
            System.out.println("Server exception :" + e.getMessage());
        }

        //CHOOSE

        //MODIFY VECTOR

    }

}


