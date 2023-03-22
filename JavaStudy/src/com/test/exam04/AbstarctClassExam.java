package com.test.exam04;

public class AbstarctClassExam { // 기존 클래스 + 설계도
    public static void main(String[] args) {
        ATeam aTeam = new ATeam();
        aTeam.manger();
        aTeam.companyGoal();
        aTeam.departGoal();

        // SalePlan salePlan = new  
    }
}


abstract class SalePlan {
    public void companyGoal() {
        System.out.println("2024 전사목표 300억 달성 ");
    }

    abstract public void departGoal(); // 추상 메소드 {} 중괄호 실행부가 없음
    abstract public void product();
}

class ATeam extends SalePlan {

    public void manger() {
        System.out.println("A팀 팀장 : 김밥마");
    }

    @Override
    public void departGoal() {
        System.out.println("A팀 핀메먹펴 70억 달성");
    }

    @Override
    public void product() {
        System.out.println("A팀 주력상품 : TV");

    }
    
}

class BTeam extends SalePlan {

    public void manger() {
        System.out.println("B 팀 팀장 : 김철수");
    }

    @Override
    public void departGoal() {
        System.out.println("B 팀 팀장 : 김철수");
        
    }

    @Override
    public void product() {
        System.out.println("B 팀 팀장 : 김철수");

    }
    
}