package deef;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Convert {
    // TO USE UDP DATAPACKET
    //Convert int[] to byte[]
    public static byte[] intsToBytes(int[] ints){
        ByteBuffer bb = ByteBuffer.allocate(3*4);
        IntBuffer ib = bb.asIntBuffer();
        for(int i=0; i<ints.length; i++){
            ib.put(ints[i]);
        }
        return bb.array();
    }

    //Convert byte[] to int[]
    public static int[] bytesToInts(byte[] bytes){
        int[] ints = new int[bytes.length/4];
        ByteBuffer.wrap(bytes).asIntBuffer().get(ints);
        return ints;
    }
}
