package Assignment3.Process1;

import Assignment3.Util.*;

import java.util.ArrayList;
import java.util.List;

public class Process1 {
    public static int loop1;
    public static List<int[]> rec_key1 = new ArrayList<>();
    public static boolean inuse1 = false;

    public static void main(String[] args) {
        int port1 = 6000;
        int port2 = 7000;
        int p_id = 1;

        Receive rv = new Receive(port1, p_id);
        Thread th0 = new Thread(rv);
        th0.start();

        Readfile rf = new Readfile(port2, p_id);
        Thread th1 = new Thread(rf);
        th1.start();

    }
}
