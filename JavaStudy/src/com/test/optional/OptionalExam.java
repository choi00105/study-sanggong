package com.test.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalExam {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("Hello");
        list1.add("Java");
        list1.add("World");
        
    // 1. ifPresent() -> 실행 구문에서 null이 발생하면 실행되지 않도록 함,
        Optional<List<String>> optional1 = Optional.of(list1);
        optional1.ifPresent(s -> System.out.println(s));

        List<String> list2 = null;
        // System.out.println(list2.get(0));
        Optional<List<String>> optional2 = Optional.ofNullable(list2);
        optional2.ifPresent(s -> System.out.println("안뚜너" + s));


    // 2. isPresent -> Optional 객체의 값이 null 이거나 false 값이 발생되면 false를 반환 , 아닌면 true
        System.out.println(Optional.of("TEST").isPresent()); // Optional 객체에 값이 있어서 true 출력
        System.out.println(Optional.ofNullable(null).isPresent()); // Optional 객체에 null 이어서 false
        System.out.println(Optional.of("TEST").filter(v -> "EST".equals(v)).isPresent()); 
        // Optional 객체에 false 값이 생성되기 때문에 false 리턴

    // 3. orElse() -> 값이 존재하면 값을 리턴 그렇지 않으면 orElse() 내의 인자 값을 리턴
        String a = "ABCD";
        String result;

        Optional<String> optional3 = Optional.ofNullable(a);
        result = optional3.filter(s -> s.startsWith("AB")).orElse("AB 로 시작하는 값이 없어요");
        System.out.println(result);

    // 4. map()
        // 예외처리를 메소드로 만듬
        System.out.println(Optional.of("XYZ").map(String::toLowerCase).orElse("오류 발생"));
    }
}
