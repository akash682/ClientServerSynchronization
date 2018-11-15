package deef;

public class Device2 {
    public static void main(String[] args) {
        //Define/Initialization
        int vec2i[] = new int[3];
        for(int i=0; i<3; i++){
            vec2i[i] = 2*i;
        }
        int port = 5000;


        //SEND
        Send send2 = new Send(vec2i, port);
        send2.start();

    }

}




