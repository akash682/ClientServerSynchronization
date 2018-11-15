package deef;

public class Device3 {
    public static void main(String[] args) {
        //Define/Initialization
        int vec3i[] = new int[3];
        for(int i=0; i<3; i++){
            vec3i[i] = 4*i;
        }
        int port = 5000;


        //SEND
        Send send2 = new Send(vec3i, port);
        send2.start();

    }

}




