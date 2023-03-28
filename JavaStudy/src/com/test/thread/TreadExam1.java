package com.test.thread;

class ExamCalss implements Runnable {

    @Override
    public void run() {
        System.out.println("Tread가 실행");
        
    }
    
}

public class TreadExam1 {
    public static void main(String[] args) {
        Thread thread = new Thread(new ExamCalss());
        thread.start();
    }
}
