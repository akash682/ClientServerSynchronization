package deef;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Convert {
    public static byte[] intsToBytes(int[] ints){
        ByteBuffer bb = ByteBuffer.allocate(3*4);
        IntBuffer ib = bb.asIntBuffer();
        for(int i=0; i<ints.length; i++){
            ib.put(ints[i]);
        }
        return bb.array();
    }

    public static int[] bytesToInts(byte[] bytes){
        int[] ints = new int[bytes.length/4];
        ByteBuffer.wrap(bytes).asIntBuffer().get(ints);
        return ints;
    }
}
