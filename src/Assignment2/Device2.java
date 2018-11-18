package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Device2 {
    //GLOBAL LIST TO STORE RECEIVED VECTOR
    public static volatile List<int[]> myList1 = new ArrayList<int[]>();
    public static int[] vec1 = new int[4];
    public static boolean inuse_list1 = false;
    public static boolean inuse_vec1 = false;

    public static void main(String[] args) {
        //INITIALIZATION
        for (int i = 0; i < vec1.length-1; i++) {
            vec1[i] = 0;
        }
        int p_id = 1;
        vec1[vec1.length-1] = p_id;
        int port0 = 5000;
        int port1 = 6000;
        int port2 = 7000;
        int[] vec1_result = new int[3];

        //INITIALVECTOR PRINT
        System.out.println("Initial Vector :" + Arrays.toString(vec1) + "\n---------------------------");

        //RECEIVE & ADDLIST
        Recieve rec = new Recieve(port1, p_id);
        Thread th0 = new Thread(rec);
        th0.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //CHOOSE & MODIFY
        Choose ch = new Choose(p_id);
        Thread th2 = new Thread(ch);
        th2.start();



        //LOCAL EVENT
        if(inuse_vec1 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec1 = true;
            vec1[p_id] += 1;
            System.out.println("---------------------------\nLocal Event Occured!");
            System.out.println("Current Vector :" + Arrays.toString(Device2.vec1)+ "\n------------------------");
            inuse_vec1 = false;
        }



        //SEND 1 -> 0
        if(inuse_vec1 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec1 = true;
            Send send10 = new Send(vec1, port0);
            Thread th3 = new Thread(send10);
            th3.start();
            inuse_vec1 = false;
        }

        //SEND 1-> 2
        if(inuse_vec1 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec1 = true;
            Send send12 = new Send(vec1, port2);
            Thread th4 = new Thread(send12);
            th4.start();
            inuse_vec1 = false;
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for(int i = 0; i<vec1_result.length; i++) {
            vec1_result[i] = vec1[i];
        }

        System.out.println("Vector :" + Arrays.toString(vec1_result));

        th0.stop();
        th2.stop();

        System.exit(0);

    }

}