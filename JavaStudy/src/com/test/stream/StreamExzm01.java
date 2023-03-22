package com.test.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamExzm01 {
    /*
     * stream API 정의 및 등자 배경
     * - 자바에서는 많은 양의 데이터를 저장하기 위해 배열이나 컬렌션(Collect Framework)을 사용
     * 이렇게 저장된 데이터에 접근하기 위해서는 반복문이나 반복자(스프링에서 배움)에서 매번 새로운 코드를 작성해야 함 , 이렇게 작성된 코드는 길이가 길고 가독성이 떨어지며, 코드의 재사용이 불가능 함. 즉 DB의 쿼리와 같이 정형화 된 처리 패턴을 가지지 못했기에 데이터 마다 다른 방법으로 접근해서 작업을 했음
     * 이러한 문제점을 극복 하기 위해 Java SE 8 부터 함수형 프로그램의 특성을 가진 stream 이라는 API ( 객체 )를 도입
     */


     /*
      * stream API의 특성

1. 데이터 접근 해서 읽고, 쓰기, 수정에 최적화된 메소드를 제공
2. 재사용이 가능한 컬렉션과 달리 단 한번만 사용 → 재사용 불가
3. 원본 데이터를 변경하지 않음
4. 스트림 연산은 filter-map 기반의 API를 사용하여 lazy 연산을 통해 성능을 최적화
5. 스트림 연산은 병렬 처리를 지원

stream API의 동작 흐름

데이터 소스 → 스트림 생성 → 중개연산(필터) → 중개연산(맵) → 최종연산(출력)

중개 연산은 둘 다 옵션
      */


    public static void main(String[] args) {
    // 1~10 까지의 정수를 갖는 List에서 6보다 작고 짝수인 요소를 찾아 10배 시킨 리스트를 출력
    // 1. 데이터 소스 생성

    // 리스트에 한번에 넣기
        List<Integer> list = Arrays.asList(1,2,3,4,2,5,6,7,8,9,10, 4);

        list.stream() // 2. 스트림 생성
            .filter(i-> i<6) // 3. 필터 연산
            .filter(i-> i%2==0)
            .map(i -> i*10) // 4. 맵 연산
            .collect(Collectors.toList())   // 5. 최종 연산 -> 출력
            .forEach(result -> System.out.println(result));

        
        System.out.println("\n=================\n");
    // 1. 스트림 생성 
    // 리스트 객체부터 스트림 생성
    // 하나씩 넣기
        List<Integer> ll = new ArrayList<>();

        ll.add(4);
        ll.add(2);
        ll.add(3);
        ll.add(1);

        // 컬렉션 에서 스트림 생성
        Stream<Integer> stream = ll.stream(); 
        stream.forEach(System.out::println);

        System.out.println("\n=================\n");


    // 배열로 부터 스트림 생성
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        // 베얄에사 스트림 생성
        IntStream stream1 = Arrays.stream(arr);
        stream1.forEach(System.out::print);

        System.out.println("\n=================\n");

    
    // 가변 매개변수로 부터 스트림 생성
        Stream<Double> stream2 = Stream.of(4.2, 2.5, 3.1, 1.9);
        stream2.forEach(System.out::println);

    System.out.println("\n=================\n");


    // 스트림 필터링 예제 : 스트림에서 중복된 요소를 제거하고 홀수만을 골라 내시오
        IntStream stream3 = IntStream.of(7,5,5,2,1,2,3,5,4,6);
        stream3.distinct().filter(n -> n%2 != 0).forEach(System.out::println);

    System.out.println("\n=================\n");

    
    // 스트림 변환 : 다음의 문자열들에서 각각의 문자열의 길이를 구하시오 
        Stream<String> stream4 = Stream.of("HTML", "CSS", "JS", "JAVA");
        stream4.map(s -> s.length()).forEach(System.out::println);

    // 스트림 변환 : 여러 문자열이 저장된 배열을 각 문자열에 포함된 단어로 이루어진 스트림으로 변환
        String[] str = {"I study hard", "You study JAVA", "I am hungry"};
        Stream<String> stream5 = Arrays.stream(str);
        stream5.flatMap(s -> Stream.of(s.split(" "))).forEach(System.out::println);


    // 스트림 제한
    // limit() : 해당 스트림의 첫번째 요소 부터 전달 된 개수만큼의 요소만으로 이루어진 새로운 스트림을 반환
    // skip() : 해당 스트림의 첫번째 요소부터 잔달된 개수만큼을 제외한 나머지 요소로만 이루어진 새로운 스트림을 반환
        // IntStream stream6 = IntStream.range(0, 10);
        IntStream stream7 = IntStream.range(0, 10);
        IntStream stream8 = IntStream.range(0, 10);

        // stream6.skip(4).forEach(n -> System.out.print(n + " "));
        stream7.limit(5).forEach(n -> System.out.print(n + " "));
        stream8.skip(3).limit(5).forEach(n-> System.out.print(n + " "));

        System.out.println();
    // 스트림 정렬
    // 1 오름차순
        Stream<String> stream9 = Stream.of("Park", "Kim", "Lee", "Choi");
        stream9.sorted().forEach(s -> System.out.print(s + " "));
    
    System.out.println();
    // 2 내림 차순
        // stream9 재사용이 불가해 새로 만들어야함
        Stream<String> stream10 = Stream.of("Pack", "Kim", "Lee", "Choi"); 
        stream10.sorted(Comparator.reverseOrder()).forEach(s -> System.out.print(s + " "));



    // 1. 요소의 출력 : forEach()
    // 2. 요소의 소모 : reduce() -> 첫번째 두번째 요소를 가지고 연산을 수행한 뒤 그 결과와 세번째 요소를 가지고 또 다시 연산을 수행하고 앞서 연산한 내용은 메모리에서 삭제
    // 3. 요소의 검색 : findFirst(), findAnt()
    // 4. 요소의 검사 : anyMatch(), allMatch(), noneMatch() 
    // 5. 요소의 통계 : count(), min(), max()
    // 6. 요소의 연산 : sum(), average()
    // 7. 요소의 수집 : collect() -> 스트림의 요소들을 컬렉션으로 변환

    System.out.println();
    // reduce 메소드를 이용하여 1~10 까지의 합 구하기
        Stream<Integer> number1 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Optional<Integer> sum1 = number1.reduce((x, y) -> x + y);
        sum1.ifPresent(s -> System.out.println("sum = " + s));

    // collect 메소드 --> 스트림 요소들을 Collectors,joining을 사용하여 ,로 구분해서 문자열로 합침
        Stream<String> fruits = Stream.of("banan", "apple", "mango", "peach");
        String result = fruits.map(Object::toString).collect(Collectors.joining(", "));
        // String result = fruits.map(Object::toString).collect(Collectors.toList()).toString(); // [] 안에 들어감
        System.out.println(result);

    
    // 열거된 문자열 중에 a를 포함한 단어를 출력해 보세요.
        Stream<String> stream11 = Stream.of("aa", "b", "c", "ab");
        System.out.println(stream11.filter(n -> n.contains("a")).collect(Collectors.joining(",")));

    // 열거된 문자열을 모두 대문자로 바꿔보세요
       Stream<String> stream12 = Stream.of("aa", "b", "c", "ab");
       System.out.print(stream12.map(s -> s.toUpperCase()).collect(Collectors.joining(",")));
       
        

    }


}
