package com.ericsson.eliokog.java8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by eliokog on 2017/4/25.
 */
public class testStream {
    /**
     * Test for combine stream
     */
    public static void main(String[] args) {
//        List<Integer> list1 = Arrays.asList(1,2,3);
//        List<Integer> list2 = Arrays.asList(3,4);
//        List<int[]> combineList = combineList(list1, list2);
//        combineList.forEach(s-> {
//            System.out.println(Arrays.toString(s));
//        });
        fizzBuzz(100);
    }

    public static List<int[]> combineList(List<Integer> firstList, List<Integer> secondList){
       return firstList.stream()
                .flatMap(i->
                        secondList.stream()
                                .map(j->new int[]{i,j})
                ).collect(toList());

    }

//    /**
//     * 三元数
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        Stream stream = IntStream.range(1, 100).boxed().flatMap(i ->
//                IntStream.range(i, 100).filter(j ->
//                        Math.sqrt(i * i + j * j) % 1 == 0)
//                        .mapToObj(b -> new int[]{i, b, (int) Math.sqrt(i * i + b * b)}));
//
//        stream.forEach(s->System.out.println(Arrays.toString((int[])s)));
//
//    }
//    public static void main(String[] args) {
//        generateFaboncci();
//    }


    public static void generateFaboncci(){
        Stream.iterate(new long[]{0,1}, n-> new long[]{n[1], n[0]+n[1]}).limit(100).forEach(s->System.out.println(Arrays.toString(s)));
    }

    private static void fizzBuzz(int n){
//        IntStream.range(0,n).forEach(i ->{
//            if(i%15==0){
//                System.out.println("FizzBuzz");
//            }else if(i%3==0){
//                System.out.println("Fizz");
//            }else if(i%5==0){
//                System.out.println("Buzz");
//            }else{
//                System.out.println(i);
//            }
//
//        });

      List<String> list =  IntStream.range(0,n).mapToObj(i -> i%15==0?"FizzBuzz":(i%3==0?"Fizz":(i%5==0?"Buzz":String.valueOf(i)))).collect(toList());
      list.forEach(System.out::println);

    }
}
