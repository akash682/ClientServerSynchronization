package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Device1 {
    //GLOBAL LIST TO STORE RECEIVED VECTOR
    public static volatile List<int[]> myList0 = new ArrayList<int[]>();
    public static int[] vec0 = new int[4];
    public static boolean inuse_list0 = false;
    public static boolean inuse_vec0 = false;

    public static void main(String[] args) {
        //INITIALIZATION
        for (int i = 0; i < vec0.length-1; i++) {
            vec0[i] = 0;
        }
        int p_id = 0;
        vec0[vec0.length-1] = p_id;
        int port0 = 5000;
        int port1 = 6000;
        int port2 = 7000;
        int[] vec0_result = new int[3];

        //INITIALVECTOR PRINT
        System.out.println("Initial Vector :" + Arrays.toString(vec0) + "\n---------------------------");

        //RECEIVE & ADDLIST
        Recieve rec = new Recieve(port0, p_id);
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
        if(inuse_vec0 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec0 = true;
            vec0[p_id] += 1;
            System.out.println("---------------------------\nLocal Event Occured!");
            System.out.println("Current Vector :" + Arrays.toString(Device1.vec0)+ "\n------------------------");
            inuse_vec0 = false;
        }


        //SEND 0 -> 1
        if(inuse_vec0 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec0 = true;
            Send send01 = new Send(vec0, port1);
            Thread th3 = new Thread(send01);
            th3.start();
            inuse_vec0 = false;
        }


        //SEND 0-> 2
        if(inuse_vec0 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec0 = true;
            Send send02 = new Send(vec0, port2);
            Thread th4 = new Thread(send02);
            th4.start();
            inuse_vec0 = false;
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for(int i=0; i<vec0_result.length; i++) {
            vec0_result[i] = vec0[0];
        }


        System.out.println("Vector :" + Arrays.toString(vec0_result));

        th0.stop();
        th2.stop();

        System.exit(0);

    }

}




