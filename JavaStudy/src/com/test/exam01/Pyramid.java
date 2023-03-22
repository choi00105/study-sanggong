package com.test.exam01;

public class Pyramid {

    public static void main(String[] args) {
     
    int num = 10;
    System.out.println("\n왼쪽에서 오른쪽으로 *이 하나씩 증가\n");
    
    for(int i=0;i<num;i++) {
        for(int j=0;j<=i;j++) 
            System.out.print("*");
        System.out.println("");
    }
    
    System.out.println("\n오른쪽에서 왼쪽으로 *이 하나씩 증가\n");
    for(int i=0;i<num;i++) {
    for(int j=0;j<(num-1)-i;j++)
        System.out.print(" ");
    for(int j=0;j<=i;j++)
        System.out.print("*");
        System.out.println("");
    }
    System.out.println("\n다이아몬드형 출력\n");
for (int i = 0; i < num; i++) {
	for (int j = 1; j < num - i; j++) 
		System.out.print(" ");
	for (int k = 0; k < i * 2 + 1; k++) 
		System.out.print("*");
	System.out.println();
}
 
	for (int i = num-1; i > 0; i--) {
		for (int j = num - i; j > 0; j--) 
			System.out.print(" ");
		for (int k = i * 2 - 1; k > 0; k--) 
			System.out.print("*");
		System.out.println();
	}
  }
}

    