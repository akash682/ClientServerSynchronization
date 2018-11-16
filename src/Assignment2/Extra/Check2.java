package deef;

import java.util.ArrayList;
import java.util.List;

public class Check2 {
    public static void main(String[] args) {
        List<int[]> myList = new ArrayList<int[]>();
        int[] arr = {0,1,2};
        myList.add(arr);

        int[] arr1 = {10,11,12};
        myList.add(arr1);

        int[] arr2 = {20,21,22};
        myList.add(arr2);

        int[] arr3 = {30,31,32};
        myList.add(arr3);

        for(int i=0; i<myList.size(); i++){
            myList.remove(i);
        }


    }
}
