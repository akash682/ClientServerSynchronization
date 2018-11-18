package Assignment3;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Process0 {
    public static int loop0 = 1;
    public static int counter;
    protected static int[] key_initial = new int[1];
    public static String cwd = System.getProperty("user.dir");
    public static File file = new File(cwd + "\\src\\Assignment3\\Counter.txt");

    public static void main(String[] args) {
        int port0 = 5000;
        int port1 = 6000;

        System.out.println("Process0");
        //GIVE KEY TO PROCESS0 at first
        counter = 0;
        if (loop0 == 1) {
            key_initial[0] = 1111;
        }


        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("0");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //RECEIVE -> READFILE -> SEND
        Receive rv = new Receive(port0, port1);
        Thread th0 = new Thread(rv);
        th0.start();


    }
}

class Convert {
    // TO USE UDP DATAPACKET
    //Convert int[] to byte[]
    public static byte[] intsToBytes(int[] ints) {
        ByteBuffer bb = ByteBuffer.allocate(4 * 4);
        IntBuffer ib = bb.asIntBuffer();
        for (int i = 0; i < ints.length; i++) {
            ib.put(ints[i]);
        }
        return bb.array();
    }

    //Convert byte[] to int[]
    public static int[] bytesToInts(byte[] bytes) {
        int[] ints = new int[bytes.length / 4];
        ByteBuffer.wrap(bytes).asIntBuffer().get(ints);
        return ints;
    }
}

class Receive implements Runnable {
    private int port_rec;
    private int port_send;
    private int[] key;


    public Receive(int port_rec, int port_send) {
        this.port_rec = port_rec;
        this.port_send = port_send;
    }

    @Override
    public void run() {

        try (DatagramSocket socket = new DatagramSocket(port_rec)) {
            while (true) {
                if (Process0.loop0 <= 100) {
                    System.out.println("----------------------------\nLoop :" + Process0.loop0);
                    if (Process0.loop0 != 1) {
                        System.out.println("WAITING FOR THE KEY...");
                        //PREPARE UDP BUFFER TO RECIEVE
                        byte[] buffer = new byte[4];
                        DatagramPacket packet0 = new DatagramPacket(buffer, buffer.length);
                        //RECEIVE
                        socket.receive(packet0);

                        //Convert byte[] to int[]
                        Convert con = new Convert();
                        key = con.bytesToInts(buffer);
                        System.out.println("Received key :" + key[0]);
                    } else {
                        key = Process0.key_initial;
                        Thread.sleep(10000);
                    }

                    if (key[0] == 1111) {
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(Process0.file));
                            int counter_read = Integer.parseInt(br.readLine());
                            br.close();

                            counter_read++;

                            String counter_write = String.valueOf(counter_read);
                            BufferedWriter bw = new BufferedWriter(new FileWriter(Process0.file));
                            bw.write(counter_write);
                            bw.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Key Error.");
                    }

                    try {
                        //CONVERT INT[](PARAMETER) to BYTE[]
                        Convert con = new Convert();
                        byte[] arrb = con.intsToBytes(key);

                        //OBTAIN LOCAL ADDRESS
                        InetAddress address = InetAddress.getLocalHost();

                        //UDP SOCKET
                        DatagramSocket datagramSocket = new DatagramSocket();
                        DatagramPacket packet1 = new DatagramPacket(arrb, arrb.length, address, port_send);

                        //SEND
                        datagramSocket.send(packet1);
                        System.out.println("Key sent to Process1.");
                        key = null;

                        Process0.loop0++;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.exit(0);
                }
            }


        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}