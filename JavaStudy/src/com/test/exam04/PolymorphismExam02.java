package com.test.exam04;

interface Animal {
    public void move(); 
}
// 인터페이스 하나 만들고
class Human implements Animal{
    @Override
    public void move() {
        System.out.println("사람이 두발로 걷습니다.");
    }
}
class Tiger implements Animal{
    public void move() {
        System.out.println("호렁이가 네발로 뜁니다.");
    }
}

class Eagle implements Animal{
    public void move() {
        System.out.println("독수리가 하능릉 납니다.");
    }
}

class AnimalMove {
    Animal animal;
    public AnimalMove(Animal animal){
        this.animal = animal;
        animal.move();
    }
}

public class PolymorphismExam02 {
    public static void main(String[] args) {
        // 메인 메소드는 편의상 어떤 클래스내의 메소드로 등록은 되지만
        // 메인 메소드를 품고있는 클래스와는 하등의 관계가 없다고 봄
        // PolymorphismExam02 animalTest = new PolymorphismExam02();
        // animalTest.moveAnimal(new Human());
        // animalTest.moveAnimal(new Tiger());
        // animalTest.moveAnimal(new Eagle());
    
        //익명 생성자 만들기
        new AnimalMove(new Human());
        new AnimalMove(new Tiger());
        new AnimalMove(new Eagle());
    }
    
    
    public void moveAnimal(Animal animal){
        animal.move();
    }
}
