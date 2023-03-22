package com.test.exam05;

import java.util.*;

class GStack<T> {
    public int tos;
    Object[] stck;
    // Object --> 모든 자바 클래스의 최상위 부모 클래스, 모든 데이터 타입을 다룰수 있음
    public GStack() {
        this.tos = 0;
        stck = new Object[10];
        
    }

    public void push(T item) {
        if(tos == 10) return;
        stck[tos] = item;
        tos++;
    }

    public T pop() {
        if(tos == 0) return null;
        tos--;
        return (T)stck[tos];
    }
}


public class GenericTest {
    public static void main(String[] args) {
        GStack<Object> stringStack = new GStack<>();        
        stringStack.push("Seoul");
        stringStack.push("Busan");
        stringStack.push("LA");
        stringStack.push(10);
        stringStack.push(9);
        
        for(int i=0;i<5;i++)
	        System.out.println(stringStack.pop());

        }

        Map<String,String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        
        
    }

