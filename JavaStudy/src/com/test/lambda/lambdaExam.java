package com.test.lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;

@FunctionalInterface
interface MyLambdaFunction{
    int sum(int a, int b);
}

@FunctionalInterface
interface MyPrint {
    String printLambda();
}
public class lambdaExam {
    public static void main(String[] args) {
        //  람다식을 이용한 익명함수 2개의 인자를 받아서 익명 메소드로 계산 후 반환( -> )

        // 2개의 인자를 받아서 계산 후 반환 하는 함수르 ㄹ람다 표기법으로 선언 
        MyLambdaFunction myLambdaFunction = (int a, int b) -> a + b;
        System.out.println("람다식을 이용한 익명 함수 :\n 2개의 인자를 받아서 익명 메소드로 계산 후 반환하는 예 = " + myLambdaFunction.sum(5, 3));

        // 인자 없이 결과값을 반환 하는 함수를 람다 표기법으로 생성
        MyPrint myPrint = () -> "HELLO WORLD!";
        System.out.println("myPrintLambda"+myPrint.printLambda());

        // 1. 매개변수 없이 반환값만 갖는 함수형 인터페이스 시용
        Supplier<String> supplier = () -> "안녕 하세요?";
        System.out.println("supplier.get() " + supplier.get());

        // 2. Consumer : 객체 T를 매개변수로 받아서 사용 하고 반환 값은 없는 함수형 인터페이스 사용
        Consumer<String> consumer = (s) -> System.out.println(s.split(" ")[0]);
        consumer.accept("안녕하세용? 반갑습니다."); // 만든 함수를 사용해서 실행
        consumer.andThen(System.out::println).accept("안녕하세용? 반갑습니다."); // 메소드 참조에 대한 람다 표기법

        // 3. Function : 객체 T를 매개변수로 받아서 처리한 후 R로 반환하는 함수형 인터페이스 사용
        Function<String, Integer> function = s -> s.length(); // 함수생성 (s)의 괄호를 빼도 실행이 된다
        System.out.println("function.apply(\"Hello World!\") = " + function.apply("Hello World!"));

        // 4. Predicate : 객체 T를 매개변수로 받아 처리한 후 Boolean으로 반환
        Predicate<String> predicate = s -> s.equals("Hello World!"); 
        System.out.println("predicate.test(\"Hello My World!\") = " + predicate.test("Hello My World!"));

    }
    
}
