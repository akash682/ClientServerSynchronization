package deef;

import java.util.ArrayList;
import java.util.List;

public class Check {
    public  static List<int[]> myList1 = new ArrayList<int[]>();

    public static void main(String[] args) {

        int[] arr = {0,1,2};
        myList1.add(arr);

        int[] arr1 = {10,11,12};
        myList1.add(arr1);

        printlist_copy pr1 = new printlist_copy();
        Thread th2 = new Thread(pr1);
        th2.start();
    }
}
