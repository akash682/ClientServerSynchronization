package Assignment3.Util;

import Assignment3.Process0.Process0;
import Assignment3.Process1.Process1;
import Assignment3.Process2.Process2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Readfile implements Runnable {
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private int p_id;
    private int port;
    private int key_read;

    public Readfile( int port, int p_id) {
        this.port = port;
        this.p_id = p_id;
    }

    @Override
    public void run() {
        File file = new File("C:\\Users\\Akash Lohani\\Programming\\Java\\DS-Project\\Project2-ver2\\src\\Assignment3\\Counter.txt");
        while(true) {
            if (p_id == 0) {
                if( Process0.rec_key0 == null){
                    continue;
                }else {
                    key_read = Process0.rec_key0.get(0)[0];

                    if(key_read == 1111){
                        try {
                            br = new BufferedReader(new FileReader(file));
                            bw = new BufferedWriter(new FileWriter(file));
                            int counter = br.read();
                            counter++;
                            bw.write(counter);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        continue;
                    }

                    try {
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
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            } else if (p_id == 1) {
                if( Process1.rec_key1 == null){
                    continue;
                }else {
                    key_read = Process1.rec_key1.get(0)[0];
                    if(key_read == 1111){
                        try {
                            br = new BufferedReader(new FileReader(file));
                            bw = new BufferedWriter(new FileWriter(file));
                            int counter = br.read();
                            counter++;
                            bw.write(counter);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }else{
                        continue;
                    }
                    try {
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
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            } else if (p_id == 2) {
                if( Process2.rec_key2 == null){
                    continue;
                }else {
                    key_read = Process2.rec_key2.get(0)[0];

                    if(key_read == 1111){
                        try {
                            br = new BufferedReader(new FileReader(file));
                            bw = new BufferedWriter(new FileWriter(file));
                            int counter = br.read();
                            counter++;
                            bw.write(counter);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        continue;
                    }

                    try {
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
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }



        }

    }
}

