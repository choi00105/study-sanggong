package com.test.thread;

public class TreadExam3 {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() { // 비동기 처리 부분

            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    try {
                        Thread.sleep(1000);
                        System.out.println("Hello 철수");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }).start();
        for(int i = 0; i<10; i++) { // 동기 처리 부분
            Thread.sleep(1000);
            System.out.println("Hello 민수");
        }

        for(int i =0; i<10; i++) {
            Thread.sleep(1000);
            System.out.println("Hello 현주");

        }
    }  
}
