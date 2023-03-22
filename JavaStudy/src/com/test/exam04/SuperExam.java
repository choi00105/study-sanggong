package com.test.exam04;

class Point {
    int x = 10;
    int y = 20;
    public Point(){} // 기본생성자 습관적으로...
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    } 
}

class Point3D extends Point {
    int z = 30;
    public Point3D(){
        this(100, 200, 300); // 인자가 3개인 Point3D의 생성자를 의미...
    }
    public Point3D(int x, int y, int z) {
        super(x,y); // 부모 클래스인 Point 클래스의 Point(int x, int y)를 의미
        this.z = z;
    }
}

public class SuperExam {
    
    public static void main(String[] args) {
        Point3D point3d = new Point3D();
        System.out.println("Point3D의 값 = "+ point3d.x +"\t" + point3d.y + "\t"
        + point3d.z);
    }

}
