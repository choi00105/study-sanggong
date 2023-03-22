package com.test.tread;

// Thread 라는 추상 클래스를 상속 받음
class ExamCalss1 extends Thread {
    @Override
    public void run(){
        System.out.println("Thread 시작");
    }
}

public class TreadExam2 {
    public static void main(String[] args) {
        ExamCalss1 examCalss1 = new ExamCalss1();
        examCalss1.start();
    }
}
