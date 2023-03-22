package com.test.exam04;

// 인터페이스 = 완전한 설계도(요약)
interface salesPlanInterface {
// 맴버 변수를 원래 못썼는데 버전 업 되면서 쓸 수 있게 되었지만 잘 안씀
    public void manger();
    public void goal();
    public void product();    
}

class ATeam implements salesPlanInterface{

    @Override
    public void manger() {
        System.out.println("A팀 팀장 : 김밥마");

    }

    @Override
    public void goal() {
        System.out.println("A팀 핀메먹펴 70억 달성");
    }

    @Override
    public void product() {
        System.out.println("A팀 주력상품 : TV");

    }

    public class interfaceExzm{
        public static void main(String[] args) {
            ATeam aTeam = new ATeam();
            aTeam.manger();
            aTeam.goal();
            aTeam.product();
        }
    }


}