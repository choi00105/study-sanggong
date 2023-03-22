package com.test.exam04;


class Parent {
    int a = 3;
    int b = 4;
    public void parentPrint(){
        System.out.println("부모 클래스");
    }
}

class Child extends Parent {
    int c = 5;
    int d = 6;
    public void childPrint() {
        System.out.println("자식 클래스");
    }
    @Override
    public void parentPrint(){
        System.out.println("부모 메소드를 바꿈");
    }
    
}

class Other {
    int k = 10;
}

public class InheritanceExam {
    public static void main(String[] args) {
        Child child = new Child();
        child.parentPrint();        
 
        Parent parent = new Parent();
        parent.a = 100;        

        
    }
}
