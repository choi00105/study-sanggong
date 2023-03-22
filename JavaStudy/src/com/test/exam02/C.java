package com.test.exam02;

public class C {
    
    private int i = 10;
    private int j = 11;
    private int k = 12;
    
    public C() {} // 기본 생성자 default constructor
    // 기본 생성자가 컴파일시 자동으로 소스에 삽입이 됨( 기본 생성자를 안써놓으면 )
    // 다른 생성자를 만들면 기본 생정자가 자동으로 생성이 안됨 -> 
    // 생성자를 쓰려면 기본 생성자를 습관적으로 넣으면 좋음

    
    // 생성자의 인자 형태에 따라 가각 다른 내용의 실행 구문을 만들어 줄 수 있음.
    public C(int i) {
        this.i = i;
        System.out.println("C 클래스 i = "+ this.i);
   
    } 
    public C(int j, int k) {
        this.j=j;
        this.k=k;
        System.out.println("C 클래스 j = "+ this.j);
        System.out.println("C 클래스 k = "+ this.k);
    }

    public void ccc(int i) {
        System.out.println("인자가 하나 !!!");
    }
    public void ccc(int i, int j) {
        System.out.println("인자가 두개 !!!");
    }

}

