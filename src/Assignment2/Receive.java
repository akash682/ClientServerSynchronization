package Assignment2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


class Recieve implements Runnable {
    //DEFINE
    private int[] rec_vec;
    private int port;
    private int p_id;

    //INITIALIZATION, PARAMETER
    public Recieve(int port, int p_id) {
        this.port = port;
        this.p_id = p_id;
    }

    //THREAD RUN
    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            while (true) {

                //PREPARE UDP BUFFER TO RECIEVE
                byte[] buffer = new byte[4 * 4];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                //RECEIVE
                socket.receive(packet);

                //Convert byte[] to int[]
                deef.Assignment2.Convert con = new deef.Assignment2.Convert();
                rec_vec = con.bytesToInts(buffer);

                if (rec_vec != null) {
                    if(p_id == 0) {
                        while(true) {
                            if (Device1.inuse_list0 == true) {
                                try {
                                    Thread.sleep(1);
                                    continue;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                //ADD TO GLOBAL LIST
                                Device1.inuse_list0 = true;
                                Device1.myList0.add(rec_vec);
                                System.out.println("Added Vector to list");
                                Device1.inuse_list0 = false;
                                break;
                            }
                        }
                    }else if(p_id==1){
                        while(true) {
                            if (Device2.inuse_list1 == true) {
                                try {
                                    Thread.sleep(1);
                                    continue;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                //ADD TO GLOBAL LIST
                                Device2.inuse_list1 = true;
                                Device2.myList1.add(rec_vec);
                                System.out.println("Added Vector to list");
                                Device2.inuse_list1 = false;
                                break;
                            }
                        }

                    }else if(p_id==2){
                        while(true) {
                            if (Device3.inuse_list2 == true) {
                                try {
                                    Thread.sleep(1);
                                    continue;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                //ADD TO GLOBAL LIST
                                Device3.inuse_list2 = true;
                                Device3.myList2.add(rec_vec);
                                System.out.println("Added Vector to list");
                                Device3.inuse_list2 = false;
                                break;
                            }
                        }

                    }
                }
            }

        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}



