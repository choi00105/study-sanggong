package com.test.exam01;

import java.util.Arrays;

public class ArrayExam {
    // 런 하려면 main method가 있어야함

    public static void main(String[] args) {
        int[] a= new int[]{1, 2, 3, 4, 5};
        System.out.println("배열 a "+ a);
        // 힙영역의 a[0] 주소값이 나옴

        System.out.println("배열 a의 값은 toString : "+Arrays.toString(a));

        for(int i=0; i<a.length; i++)
            System.out.print(a[i] + " ");

        for(int b:a) 
            System.out.print(b + " ");

        System.out.println("");
        
        int[][] b = { {1,2,3}, {4,5,6}, {7, 8, 9} };

        System.out.println("b[0][2] = " + b[0][2]);
        System.out.println("b[1][2] = " + b[1][2]);
        System.out.println("b[2][0] = " + b[2][0]);

        for(int i=0; i<b.length; i++)
            System.out.println(Arrays.toString(b[i]));

        System.out.println("비정방형 보기");
        int intArray[][] = new int[4][];
        intArray[0] = new int[3];
        intArray[1] = new int[2];
        intArray[2] = new int[3];
        intArray[3] = new int[2];
        
        for(int i=0;i<intArray.length;i++) 
        for(int j=0;j<intArray[i].length;j++)
            intArray[i][j] = (i+1)*10 + j;
        
        for(int i=0;i<intArray.length;i++) {
            for(int j=0;j<intArray[i].length;j++)
                System.out.print(intArray[i][j] + " ");
            System.out.println();
        }
            
    }

}
