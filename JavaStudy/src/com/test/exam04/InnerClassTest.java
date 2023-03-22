package com.test.exam04;

import com.test.exam04.OuterClass.InnerClass;

class OuterClass {
    public int a=3; // 맴버변수
    
        class InnerClass { // 내부 클래스

        public int b =5;

        public void innerPrint() { // 메소드 == 함수
            System.out.println("내부 클래스");
        }
    }
}

public class InnerClassTest {
    public static void main(String[] args) {
        // main 에서 선언해야 메모리에 인스턴스 생성됨

        OuterClass outerClass = new OuterClass();
        
        System.out.println("a = " + outerClass.a);

        InnerClass innerClass = outerClass.new InnerClass();
    }
}