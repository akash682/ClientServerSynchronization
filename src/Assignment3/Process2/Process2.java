package Assignment3.Process2;

import Assignment3.Util.*;

import java.util.ArrayList;
import java.util.List;

public class Process2{
    public static int loop2;
    public static List<int[]> rec_key2 = new ArrayList<>();
    public static boolean inuse2 = false;

    public static void main(String[] args) {
        int port2 = 7000;
        int port0 = 5000;
        int p_id = 2;

        Receive rv = new Receive(port2, p_id);
        Thread th0 = new Thread(rv);
        th0.start();

        Readfile rf = new Readfile(port0, p_id);
        Thread th1 = new Thread(rf);
        th1.start();

    }
}