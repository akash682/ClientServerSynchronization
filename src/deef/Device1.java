package deef;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;


public class Device1 {
    public static List<int[]> myList = new ArrayList<int[]>();

    public static void main(String[] args) {
        //Define
        int vec1[] = new int[3];
        int i;


        //Initialization
        for (i = 0; i < vec1.length; i++) {
            vec1[i] = 5 * i;
        }

        //TEST PRINT LIST
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
        try (DatagramSocket socket = new DatagramSocket(5000)) {
            System.out.println("Running Receive Thread");
            Recieve rec = new Recieve(socket);
            rec.run();
        } catch (IOException e) {
            System.out.println("Server exception :" + e.getMessage());
        }

        //MODIFY

    }

}


