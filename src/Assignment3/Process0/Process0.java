package Assignment3.Process0;

import Assignment3.Util.*;

import java.util.ArrayList;
import java.util.List;

public class Process0 {
    public static int loop0 = 0;
    public static List<int[]> rec_key0 = new ArrayList<>();
    public static boolean inuse0 = false;

    public static void main(String[] args) {
        int port0 = 5000;
        int port1 = 6000;
        int p_id = 0;
        int[] key = new int[1];


        if(loop0 == 0){
            key[0] = 1111;
            rec_key0.add(key);
        }

        Receive rv = new Receive(port0, p_id);
        Thread th0 = new Thread(rv);
        th0.start();

        Readfile rf = new Readfile(port1, p_id);
        Thread th1 = new Thread(rf);
        th1.start();

    }
}
