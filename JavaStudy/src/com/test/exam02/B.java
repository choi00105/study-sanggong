package com.test.exam02;


public class B{

    private int a = 3;
    private int b = 4;

    public int multiply(int i, int j){
        return i*j;
    }

    public int getA(){ // getter method
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    public void setA(int a) { //setter method
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

}
