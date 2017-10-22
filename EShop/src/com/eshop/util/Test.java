package com.eshop.util;

import java.sql.Connection;
import java.util.Date;
import java.util.Random;

public class Test {
    public static void main(String[] args) {

        String datestr=((Long)new Date().getTime()).toString();
        int index=datestr.length();
        Long date=Long.parseLong(datestr.substring(index-6,index))*10000;
        Long rand=(new Random().nextInt(10000)*1000000000000l);
        Long oid=rand+date+0011;
        System.out.println(date);
        System.out.println(oid);
    }
}
