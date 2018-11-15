package deef;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;


class Recieve implements Runnable {
    private DatagramSocket socket;
    private int[] rec_vec;

    public Recieve(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //Create Socket
            byte[] buffer = new byte[3 * 4];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            //Convert byte to int
            Convert con = new Convert();
            rec_vec = con.bytesToInts(buffer);
            Device1.myList.add(rec_vec);

            System.out.println("Added Vector to list");


        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }


}
