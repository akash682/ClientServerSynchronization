package deef;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


class Recieve implements Runnable {
    //DEFINE
    private DatagramSocket socket;
    private int[] rec_vec;

    //INITIALIZATION, PARAMETER
    public Recieve(DatagramSocket socket) {
        this.socket = socket;
    }

    //THREAD RUN
    @Override
    public void run() {
        try {
            //PREPARE THE BUFFER TO RECIEVE
            byte[] buffer = new byte[3 * 4];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            //RECEIVE
            socket.receive(packet);

            //Convert byte[] to int[]
            Convert con = new Convert();
            rec_vec = con.bytesToInts(buffer);

            //ADD TO GLOBAL LIST
            Device1.myList.add(rec_vec);

            System.out.println("Added Vector to list");


        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }


}
