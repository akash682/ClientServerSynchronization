package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Device3 {
    //GLOBAL LIST TO STORE RECEIVED VECTOR
    public static volatile List<int[]> myList2 = new ArrayList<int[]>();
    public static int[] vec2 = new int[4];
    public static boolean inuse_list2 = false;
    public static boolean inuse_vec2 = false;

    public static void main(String[] args) {
        //INITIALIZATION
        for (int i = 0; i < vec2.length-1; i++) {
            vec2[i] = 0;
        }
        int p_id = 2;
        vec2[vec2.length-1] = p_id;
        int port0 = 5000;
        int port1 = 6000;
        int port2 = 7000;
        int[] vec2_result = new int[3];

        //INITIALVECTOR PRINT
        System.out.println("Initial Vector :" + Arrays.toString(vec2) + "\n ----------------------------");

        //RECEIVE & ADDLIST
        Recieve rec = new Recieve(port2, p_id);
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
        if(inuse_vec2 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec2 = true;
            vec2[p_id] += 1;
            System.out.println("------------------------\nLocal Event Occured!");
            System.out.println("Current Vector :" + Arrays.toString(Device3.vec2) + "\n------------------------");
            inuse_vec2 = false;
        }


        //SEND 2 -> 0
        if(inuse_vec2 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec2 = true;
            Send send20 = new Send(vec2, port0);
            Thread th3 = new Thread(send20);
            th3.start();
            inuse_vec2 = false;
        }

        //SEND 2-> 1
        if(inuse_vec2 == true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            inuse_vec2 = true;
            Send send21 = new Send(vec2, port1);
            Thread th4 = new Thread(send21);
            th4.start();
            inuse_vec2 = false;
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0; i<vec2_result.length; i++) {
            vec2_result[i] = vec2[1];
        }

        System.out.println("Vector :" + Arrays.toString(vec2_result));
        th0.stop();
        th2.stop();

        System.exit(0);

    }

}




