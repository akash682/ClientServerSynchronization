package Assignment2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class Send implements Runnable{
    //DEFINE
    private int[] arr;
    private int port;

    //INITIALIZATION , PARAMETER
    public Send(int[] arr, int port) {
        this.arr = arr;
        this.port = port;
    }

    //THREAD RUN
    @Override
    public void run(){
        try{
            //CONVERT INT[](PARAMETER) to BYTE[]
            Convert con = new Convert();
            byte[] arrb = con.intsToBytes(arr);

            //OBTAIN LOCAL ADDRESS
            InetAddress address = InetAddress.getLocalHost();

            //UDP SOCKET
            DatagramSocket datagramSocket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(arrb, arrb.length, address, port);

            //SEND
            datagramSocket.send(packet);

        }catch (SocketTimeoutException e){
            System.out.println("Socket had timed out.");
        }catch (IOException e){
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
