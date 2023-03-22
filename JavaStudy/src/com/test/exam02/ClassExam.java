package com.test.exam02;

public class ClassExam {

    public static void main(String[] args) {

        A a = new A(); // 클래스 선언 -> a라는 인스턴스(instance) 생성 -> 클래스 참조변수 a(인스턴스 변수 a) 안에 A클래스 형태가 담겨져 있는 메모리 주소가 들어옴
        System.out.println("a.a = " + a.a);
        int result = a.sum(10, 20);
        System.out.println("result = "+ result);

        a.a = 5;
        System.out.println("a.a = " + a.a);
        
        A a1 = new A(); // a1 인스턴스 생성
        System.out.println("a1.a = "+a1.a);

        B b = new B();
        System.out.println("b.b = " + b.getA());

        b.setB(100);
        System.out.println("b.b =" + b.getB());

        new C(20); // 익명 클래스
        new C(30, 40);
        
        C c = new C();
        c.ccc(0);
        c.ccc(0, 1);
        
        D.ddd(); // ddd는 static method ...
        D d1 = new D(); // d1 인스턴스
        D d2 = new D(); // d2 인스턴스
        System.out.println("");
        d1.d = 4;
        System.out.println("d2.d = " + d2.d);
        // d1.d 값을 바꿨는데 d2.d가 바뀜 --> D 클래스에서 static 변수로 설정해서 메모레의 데이터 영역의 값이 바뀌었기 때문
        
        d1.e = 23;
        System.out.println("d2.e = " + d2.e);
    }
}


