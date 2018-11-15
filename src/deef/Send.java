package deef;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class Send extends Thread{
    private int[] arr1;
    private int port1;

    public Send(int[] arr, int port) {
        this.arr1 = arr;
        this.port1 = port;
    }

    @Override
    public void run(){
        try{
            Convert con = new Convert();
            byte[] arrb = con.intsToBytes(arr1);

            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket datagramSocket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(arrb, arrb.length, address, port1);

            datagramSocket.send(packet);

        }catch (SocketTimeoutException e){
            System.out.println("Socket had timed out.");
        }catch (IOException e){
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
