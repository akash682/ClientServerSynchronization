package Assignment3.Util;

import Assignment3.Process0.Process0;
import Assignment3.Process1.Process1;
import Assignment3.Process2.Process2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receive implements Runnable {
    private int port;
    private int[] key;
    private int p_id;

    public Receive(int port, int p_id) {
        this.port = port;
        this.p_id = p_id;
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            while (true) {
                System.out.println("Inside Recieve run");
                //PREPARE UDP BUFFER TO RECIEVE
                byte[] buffer = new byte[4];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                //RECEIVE
                socket.receive(packet);

                //Convert byte[] to int[]
                Convert con = new Convert();
                key = con.bytesToInts(buffer);

                //Add Received key to the list
                if (p_id == 0) {
                    while (true) {
                        System.out.println("Inside add List0");
                        if (Process0.inuse0 == true) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        } else {
                            Process0.inuse0 = true;
                            Process0.rec_key0.add(key);
                            Process0.inuse0 = false;
                            break;
                        }
                    }
                } else if (p_id == 1) {
                    while (true) {
                        System.out.println("Inside add List1");
                        if (Process1.inuse1 == true) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        } else {
                            Process1.inuse1 = true;
                            Process1.rec_key1.add(key);
                            Process1.inuse1 = false;
                            break;
                        }
                    }
                } else if (p_id == 2) {
                    System.out.println("Inside add List1");
                    while (true) {
                        if (Process2.inuse2 == true) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        } else {
                            Process2.inuse2 = true;
                            Process2.rec_key2.add(key);
                            Process2.inuse2 = false;
                            break;
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

