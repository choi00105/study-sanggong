package com.test.exam03;

import java.util.ArrayList;
import java.util.List;

public class MemberManger {
    public static void main(String[] args) {
        // Member member = new Member("mylover", "김현주", 23);

        // member.setUserId("stallar");
        // member.setUserName("김철수");
        // member.setAge(23);

        List<Member> list = new ArrayList<>();
        
        // list.add(new Member("my1","김김",25));
        // list.add(new Member("my2","이이",26));
        // list.add(new Member("my3","박박",28));

        list.add(new Member.Builder()
            .userid("mylover")
            .username("김현주")
            .age(23)
            .build()
        );
        list.add(new Member.Builder()
            .userid("mylover2")
            .username("이현주")
            .age(25)
            .build()
        );
        list.add(new Member.Builder()
            .userid("mylover3")
            .username("박현주")
            .age(27)
            .build()
        );
        for(Member member:list){
            System.out.println("userId = " + member.getUserId());
            System.out.println("userName = " + member.getUserName());
            System.out.println("userAge = " + member.getAge());
        }
        
        // 실무에서 쓰는 느낌
        list.stream().map(n -> n.getUserName()).sorted().forEach(s -> System.out.println(s));
        
    }

    
}
