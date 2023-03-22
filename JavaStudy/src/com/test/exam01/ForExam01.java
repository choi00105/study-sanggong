package com.test.exam01;

public class ForExam01 {
    public static void main(String[] argv){

        int i = 0;
        int sum = 0;
        
        for(i=0; i<=100; sum += i++);
        System.out.println("\n" + "#1 For문을 이용한 계산 : sum = " + sum);
        
        i=0; 
        sum=0;
        while(i<=100) sum += i++;
        System.out.println("#2 while문을 이용한 계산 : sum = " + sum);
        
        i=0; sum =0;
        while(true){
            sum += i++;
            if(i>100) break;
        }
        System.out.println("#3 while문 + break을 이용한 계산 : sum = " + sum);
        i=0; 
        sum=0;
        while(true) {
        sum += i++;
        if(i<=100) continue;
            else break;
        }
        System.out.println("#4 while문 + continue + break을 이용한 계산 : sum = " + sum);
        
        i=0; 
        sum=0;
        do { sum += i++; 
        }while(i<=100);
        System.out.println("#5 do-while문을 이용한 계산 : sum = " + sum);

        i=0;
        sum=0;
        for(i=0; i<=100; i++){
            sum+=i;
        }
        System.out.println("for "+sum);
        int a=10;
        System.out.println(a*a/2+a/2);

        i=0;
        sum=0;
        while(i<=100) {
            sum+=i;
            i++;
        }
        System.out.println("while "+sum);
    }
          
}
