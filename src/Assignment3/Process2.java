package Assignment3;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;


public class Process2 {
    public static int loop2 = 1;

    public static void main(String[] args) {
        int port2 = 7000;
        int port0 = 5000;

        System.out.println("Process2");
        Receive2 rv = new Receive2(port2, port0);
        Thread th0 = new Thread(rv);
        th0.start();

    }
}

class Convert2 {
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

class Receive2 implements Runnable {
    private int port_rec;
    private int port_send;
    private int[] key;


    public Receive2(int port_rec, int port_send) {
        this.port_rec = port_rec;
        this.port_send = port_send;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        try (DatagramSocket socket = new DatagramSocket(port_rec)) {
            while (true) {
                if (Process2.loop2 <= 100) {
                    System.out.println("---------------------------\nLoop :" + Process2.loop2);
                    //PREPARE UDP BUFFER TO RECIEVE
                    byte[] buffer = new byte[4];
                    DatagramPacket packet0 = new DatagramPacket(buffer, buffer.length);
                    //RECEIVE
                    System.out.println("WAITING FOR THE KEY...");
                    socket.receive(packet0);

                    //Convert byte[] to int[]
                    Convert2 con = new Convert2();
                    key = con.bytesToInts(buffer);
                    System.out.println("Received key :" + key[0]);

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
                        continue;
                    }

                    try {
                        //CONVERT INT[](PARAMETER) to BYTE[]
                        byte[] arrb = con.intsToBytes(key);

                        //OBTAIN LOCAL ADDRESS
                        InetAddress address = InetAddress.getLocalHost();

                        //UDP SOCKET
                        DatagramSocket datagramSocket = new DatagramSocket();
                        DatagramPacket packet1 = new DatagramPacket(arrb, arrb.length, address, port_send);

                        //SEND
                        datagramSocket.send(packet1);
                        System.out.println("Key sent to Process0.");
                        key = null;

                        Process2.loop2++;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.exit(-1);
                }
            }


        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }


    }
}

