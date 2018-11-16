//package deef;
//
//import java.util.Arrays;
//
//public class printlist implements Runnable {
//    @Override
//    public void run() {
//        while (true) {
//            System.out.println("Inside Thread");
//            while(true) {
//                if(Device1.inuse1 == true){
//                    try {
//                        Thread.sleep(1);
//                        continue;
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }else {
//                    Device1.inuse1 = true;
//                    if (Device1.myList.size() > 0) {
//                        for (int[] rec_arr : Device1.myList) {
//                            System.out.println(Arrays.toString(rec_arr));
//                        }
//                    }
//                    Device1.inuse1 = false;
//                    break;
//                }
//            }
//        }
//    }
//}

