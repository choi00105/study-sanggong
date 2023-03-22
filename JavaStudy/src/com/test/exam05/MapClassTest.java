package com.test.exam05;

import java.util.*;

public class MapClassTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("userid", "my");
        map.put("username", "김철수");

        System.out.println(map.get("userid"));

        List<Map<String,String>> list = new ArrayList<>();
        list.add(map);

        int a = 4;

        String aa = Integer.toString(a);
         
        String b = "5";
        int c = Integer.parseInt(b);

        System.out.println(aa+c);
    }
}
