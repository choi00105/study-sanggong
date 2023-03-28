package com.text.string;

import java.util.ArrayList;

public class StringBuilderExzm {
    public static void main(String[] args) {
        // 여러개의 문자열 연결하는데 특화된 개체
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("문자열1").append("문자열2");
        // StringBuilder을 변환학기 위해서는 반드시 toString() 메서드 이용
        String str = stringBuilder.toString(); 

        System.out.println(stringBuilder);
        System.out.println(str);

        ArrayList<String> list = new ArrayList<>();
        list.add("첫번째");
        list.add("두번째");
        list.add("세번째");

        for(int i =0; i< list.size(); i++) {
            stringBuilder.append(list.get(i));
        }
        System.out.println(stringBuilder);
    }
}
