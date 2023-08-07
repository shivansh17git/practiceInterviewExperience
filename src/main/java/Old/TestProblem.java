package Old;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Array of 10
Randomly numbers are filled
Set of three number should add upto a (given number=15)
(1,2,12)
15 - (a+b)
O
 */
public class TestProblem {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,1,2,6,7,8,9};
        sumUniqueTripletToK(arr,15);
    }
    public static void sumTripletToK(int[] arr, int k) {
        Map<Integer, Integer> numberFrequency = new HashMap<>();
        for (int i=0; i < arr.length ; i++) {
            numberFrequency.put(arr[i] ,(numberFrequency.getOrDefault(arr[i] , 0)+1));
        }

        for(int i=0; i < arr.length; i++) {
            int num1 = arr[i];
            for(int j=i+1; j < arr.length; j++) {
                int num2 = arr[j];
                int num3 = k - (num1+num2);
                if(numberFrequency.containsKey(k - (num1+num2))) {
                    System.out.print("["+num1+","+num2+","+num3+"]");
                }
            }
        }
    }

    public static void sumUniqueTripletToK(int[] arr, int k) {
        Arrays.sort(arr);
        for(int i=0; i < arr.length; i++) {
            int num1 = arr[i];
            if(i==0 || num1 != arr[i-1]) {
                int l=i+1;
                int r= arr.length-1;
                while (l < r) {
                    if(l>i+1 && arr[l] == arr[l-1])
                    {
                        l++;
                    }
                    else if(r < arr.length-1 && arr[r] == arr[l+1])
                    {
                        r--;
                    }
                    else if(arr[l] + arr[r] + num1 == k) {
                        System.out.print("["+num1+","+arr[l]+","+arr[r]+"]");
                        l++;r--;
                    }
                    else if(arr[l] + arr[r] + num1 > k) {
                        r--;
                    }
                    else {
                        l++;
                    }
                }
            }
        }
    }
}
