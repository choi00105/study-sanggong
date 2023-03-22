package com.test.exam01;

import java.util.Scanner;

public class JavaStructure {

    public static int sum(int a, int b){ //a와 b를 합친 값을 돌려준다.
		return a+b;
	}

	public static void main(String[] argv){ //메인 메소드의 시작

		//변수 선언
		int i = 10;
		long l_value = 10L;
		double d_value = 10;
		float f_value = 10;
		int j;
		char a;

		j=sum(i, 10);
		a = '가';
		System.out.println("Hello, World.");
		System.out.println("Integer 타입 j = " + Integer.toString(j));
		System.out.println("double 타입 d_value = " + Double.toString(d_value));
		System.out.println("float 타입 f_value = " + Float.toString(f_value));
		System.out.println("long 타입 l_value = " + Long.toString(l_value));
		System.out.println("char 타입 a = " + a);	
		String[] names = new String[3]; //배열 선언
		int size = names.length; //배열 길이 가져 오기

		//값 입력 받기
		Scanner put = new Scanner(System.in);

		for(int index=0; index<size; index++) {
			System.out.print("이름을 입력하세요 : ");
			names[index] = put.nextLine();
		}

		//화면에 입력 받은 이름을 출력
		for(int index=0; index<size; index++) 
			System.out.println("이름 : " + names[index]);
		
		put.close();
	}
}

