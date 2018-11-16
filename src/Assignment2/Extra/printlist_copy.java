package deef;

import java.util.Arrays;

public class printlist_copy implements Runnable {
    @Override
    public void run() {
        while (true) {
            if (Check.myList1.size() > 0) {
                System.out.println("Inside Thread");
                for (int[] arr : Check.myList1) {
                           System.out.println(Arrays.toString(arr));
                }
            }
        }
    }
}

