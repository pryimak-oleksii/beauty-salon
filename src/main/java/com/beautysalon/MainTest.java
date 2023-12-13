package com.example.beautysaloneeservlets;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;

public class MainTest {
    public static void main(String[] args)
//            throws ParseException
    {
//        String sDate1 = "2022-09-13";
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(2022, 8, 17);
//        System.out.println(calendar);
//        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        int [] arr = new int[]{1, 2 , 3, 4, 5, 6, 7, 8};
//        for(int x: arr){
//            System.out.println(x);
//        }
        int [] arr2 = new int[arr.length];
//        for(int x: arr2){
//            System.out.println(x);
//        }
        System.arraycopy(arr, 1, arr2, 1, arr.length-1);
        for(int x: arr2){
            System.out.print(x + " ,");
        }
    }
}
