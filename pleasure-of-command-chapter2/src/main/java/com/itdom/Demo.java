package com.itdom;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date(1595889533000L)));

        System.out.println(System.currentTimeMillis());
    }
}
