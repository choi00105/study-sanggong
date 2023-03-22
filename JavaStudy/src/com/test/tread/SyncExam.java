package com.test.tread;

class ExamA {
    public synchronized void examA1(String thread){
        System.out.println("ExamA1 시작");
        for(int i = 0; i <3; i++) {
            System.out.println("thread : "+thread + ", ExamA1 : " + i);
        }
        System.out.println("ExamA1 끝 ...");
    }

    public synchronized void examA2(String thread) {
        System.out.println("ExamA2 시작");
        for(int i = 0; i <3; i++) {
            System.out.println("thread : "+thread + ", ExamA2 : " + i);
        }
        System.out.println("ExamA2 끝 ...");
    }
}

public class SyncExam {
    public static void main(String[] args) {
        ExamA examA = new ExamA();
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                examA.examA1("thread1");
            }
            
        });
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                examA.examA1("thread2");
            }
            
        });
        thread1.start();
        thread2.start();
        
    }
}
