package deef;

public class Device3 {
    public static void main(String[] args) {
        //DEFINE
        int vec3i[] = new int[3];

        //INITIALIZATION
        for(int i=0; i<3; i++){
            vec3i[i] = 5*i;
        }
        int port1 = 5000;

        //SEND
        Send send3 = new Send(vec3i, port1);
        send3.start();

    }

}





