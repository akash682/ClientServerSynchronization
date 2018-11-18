package Assignment2;

import java.util.Arrays;

public class Choose implements Runnable {
    private int p_id;

    public Choose( int p_id) {
        this.p_id = p_id;
    }

    @Override
    public void run() {
        while (true) {
            if (p_id == 0) {//Device 1
                while (true) {
                    if (Device1.inuse_list0 == true || Device1.inuse_vec0 == true) {
                        try {
                            Thread.sleep(1);
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Device1.inuse_list0 = true;
                        Device1.inuse_vec0 = true;
                        for (int j = 0; j< Device1.myList0.size(); j++) {
                            int[] vec_rec = Device1.myList0.get(j);
                            System.out.println("Current Vector :" + Arrays.toString(Device1.vec0));
                            System.out.println("Recieved Vector :" + Arrays.toString(vec_rec));
                            int condition = 0;
                            for (int i = 0; i < vec_rec.length - 1; i++) {
                                if (i == vec_rec[3] && (vec_rec[i] == Device1.vec0[i] + 1)) {
                                    condition++;
                                } else if (vec_rec[i] <= Device1.vec0[i]) {
                                    condition++;
                                }
                            }
                            if (condition == 3) {
                                for(int i = 0; i < Device1.vec0.length-1; i++) {
                                    Device1.vec0[i] = Math.max(Device1.vec0[i], vec_rec[i]);
                                }
                                System.out.println("Modified Vector :" + Arrays.toString(Device1.vec0)+"\n---------------------------");
                                Device1.myList0.remove(j);
                            }else{
                                System.out.println("Suspended");
                            }
                        }
                        Device1.inuse_list0 = false;
                        Device1.inuse_vec0 = false;
                        break;
                    }
                }
            } else if (p_id == 1) {//Device
                while (true) {
                    if (Device2.inuse_list1 == true || Device2.inuse_vec1 == true) {
                        try {
                            Thread.sleep(1);
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Device2.inuse_list1 = true;
                        Device2.inuse_vec1 = true;

                        for (int j = 0; j< Device2.myList1.size(); j++) {
                            int[] vec_rec = Device2.myList1.get(j);
                            System.out.println("Current Vector :" + Arrays.toString(Device2.vec1));
                            System.out.println("Recieved Vector :" + Arrays.toString(vec_rec));
                            int condition = 0;
                            for (int i = 0; i < vec_rec.length - 1; i++) {
                                if (i == vec_rec[3] && (vec_rec[i] == Device1.vec0[i] + 1)) {
                                    condition++;
                                } else if (vec_rec[i] <= Device2.vec1[i]) {
                                    condition++;
                                }
                            }
                            if (condition == 3) {
                                for(int i = 0; i < Device2.vec1.length-1; i++) {
                                    Device2.vec1[i] = Math.max(Device2.vec1[i], vec_rec[i]);
                                }
                                System.out.println("Modified Vector :" + Arrays.toString(Device2.vec1) + "\n---------------------------");
                                Device2.myList1.remove(j);
                            }else{
                                System.out.println("Suspended");
                            }
                        }
                        Device2.inuse_list1 = false;
                        Device2.inuse_vec1 = false;
                        break;
                    }
                }
            } else if (p_id == 2){//Device 3
                while (true) {
                    if (Device3.inuse_list2 == true ||Device3.inuse_vec2 == true) {
                        try {
                            Thread.sleep(1);
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Device3.inuse_list2 = true;
                        Device3.inuse_vec2 = true;
                        for (int j = 0; j< Device3.myList2.size(); j++) {
                            int[] vec_rec = Device3.myList2.get(j);
                            System.out.println("Current Vector :" + Arrays.toString(Device3.vec2));
                            System.out.println("Recieved Vector :" + Arrays.toString(vec_rec));
                            int condition = 0;
                            for (int i = 0; i < vec_rec.length - 1; i++) {
                                if (i == vec_rec[3] && (vec_rec[i] == Device3.vec2[i] + 1)) {
                                    condition++;
                                } else if (vec_rec[i] <= Device3.vec2[i]) {
                                    condition++;
                                }
                            }
                            if (condition == 3) {
                                for(int i = 0; i < Device3.vec2.length-1; i++) {
                                    Device3.vec2[i] = Math.max(Device3.vec2[i], vec_rec[i]);
                                }
                                System.out.println("Modified Vector :" + Arrays.toString(Device3.vec2)+ "\n---------------------------");
                                Device3.myList2.remove(j);
                            }else{
                                System.out.println("Suspended");
                            }
                        }
                        Device3.inuse_list2 = false;
                        Device3.inuse_vec2 = false;
                        break;
                    }
                }
            }

        }
    }
}
