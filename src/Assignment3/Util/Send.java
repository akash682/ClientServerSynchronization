package Assignment3.Util;

import Assignment3.Process0.Process0;
import Assignment3.Process1.Process1;
import Assignment3.Process2.Process2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class Send implements Runnable {

    //DEFINE
    private int port;
    private int p_id;

    //INITIALIZATION , PARAMETER
    public Send(int port, int p_id) {
        this.port = port;
        this.p_id = p_id;
    }

    @Override
    public void run() {
        try {
            if (p_id == 0) {
                while (true) {
                    Process0.inuse0 = true;
                    if (Process0.rec_key0 != null) {
                        //CONVERT INT[](PARAMETER) to BYTE[]
                        Convert con = new Convert();
                        byte[] arrb = con.intsToBytes(Process0.rec_key0.get(0));

                        //OBTAIN LOCAL ADDRESS
                        InetAddress address = InetAddress.getLocalHost();

                        //UDP SOCKET
                        DatagramSocket datagramSocket = new DatagramSocket();
                        DatagramPacket packet = new DatagramPacket(arrb, arrb.length, address, port);

                        //SEND
                        datagramSocket.send(packet);

                        Process0.rec_key0.remove(0);
                    }
                    Process0.inuse0 = false;
                }
            } else if (p_id == 1) {
                while (true) {
                    if (Process1.rec_key1 != null) {
                        //CONVERT INT[](PARAMETER) to BYTE[]
                        Convert con = new Convert();
                        byte[] arrb = con.intsToBytes(Process1.rec_key1.get(0));

                        //OBTAIN LOCAL ADDRESS
                        InetAddress address = InetAddress.getLocalHost();

                        //UDP SOCKET
                        DatagramSocket datagramSocket = new DatagramSocket();
                        DatagramPacket packet = new DatagramPacket(arrb, arrb.length, address, port);

                        //SEND
                        datagramSocket.send(packet);
                        Process1.rec_key1.remove(0);
                    }
                }
            } else if (p_id == 2) {
                while (true) {
                    if (Process2.rec_key2 != null) {
                        //CONVERT INT[](PARAMETER) to BYTE[]
                        Convert con = new Convert();
                        byte[] arrb = con.intsToBytes(Process2.rec_key2.get(0));

                        //OBTAIN LOCAL ADDRESS
                        InetAddress address = InetAddress.getLocalHost();

                        //UDP SOCKET
                        DatagramSocket datagramSocket = new DatagramSocket();
                        DatagramPacket packet = new DatagramPacket(arrb, arrb.length, address, port);

                        //SEND
                        datagramSocket.send(packet);
                        Process2.rec_key2.remove(0);
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Socket had timed out.");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
