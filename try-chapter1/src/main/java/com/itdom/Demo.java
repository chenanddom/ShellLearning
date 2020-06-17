package com.itdom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(){{
            add("123");
            add("234");
            add("345");
            add("456");
            add("567");
            add("678");
            add("789");
        }};

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if (next.contains("123")){
                list.remove("123");
            }
            if (next.contains("456")){
                list.remove("456");
            }
        }
        System.out.println(list.toString());
    }
}
