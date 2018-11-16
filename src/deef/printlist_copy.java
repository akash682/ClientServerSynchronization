package deef;

public class printlist implements Runnable {
    @Override
    public void run() {
        while (true) {
            if (Device1.myList.size() > 0) {
                System.out.println("Inside Thread");
                for (int[] arr : Device1.myList) {
//                            System.out.println(Arrays.toString(arr));
                    for (int i : arr) {
                        System.out.println(i + ",");
                    }
                    System.out.println("\n");
                }
            }
        }
    }
}

