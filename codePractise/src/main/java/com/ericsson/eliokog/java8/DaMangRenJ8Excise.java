package com.ericsson.eliokog.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by eliokog on 2017/4/26.
 */
public class DaMangRenJ8Excise {

    public static void main(String[] args) throws Exception {

//        System.out.println(countFromFile("C:\\PanGpHip.log"));
//        findTopNLongWords(("C:\\Users\\eliokog\\Documents\\My Received Files\\Distributed-Configuration-Suites-soapui-project.xml"));
//        System.out.println(countFromFile("C:\\\\Users\\\\eliokog\\\\Documents\\\\My Received Files\\\\Distributed-Configuration-Suites-soapui-project.xml"));
//        System.out.println(countFromFileParallel("C:\\\\Users\\\\eliokog\\\\Documents\\\\My Received Files\\\\Distributed-Configuration-Suites-soapui-project.xml"));
//        findTopNLongWords("C:\\\\Users\\\\eliokog\\\\Documents\\\\My Received Files\\\\Distributed-Configuration-Suites-soapui-project.xml");
//
//        zip(Arrays.asList("1", "2", "3", "4").stream(), Arrays.asList("5", "6", "7", "8").stream()).forEach(System.out::println);
        AtomicInteger[] ai = {new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0)};

        List<String> lineList = Files.readAllLines(Paths.get("C:\\Users\\eliokog\\Documents\\My Received Files\\Distributed-Configuration-Suites-soapui-project.xml"));
        List<String> stringList = lineList.parallelStream()
                .flatMap(s -> Arrays.stream(s.split(" "))).collect(Collectors.toList());
        countShortWords(stringList, ai);
        Stream.of(ai).forEach(System.out::println);
    }

    public static long countFromFile(String file) throws IOException {

        List<String> lineList = Files.readAllLines(Paths.get(file));
        long startTime = System.nanoTime();
        long i = lineList.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> s.length() > 10)
                .count();
        System.out.println("stream time used: " + (System.nanoTime() - startTime));
        return i;
    }

    public static long countFromFileParallel(String file) throws IOException {

        List<String> lineList = Files.readAllLines(Paths.get(file));
        long startTime = System.nanoTime();
        long i = lineList.parallelStream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> s.length() > 10)
                .count();
        System.out.println("parallelStream time used: " + (System.nanoTime() - startTime));
        return i;
    }

    public static void findTopNLongWords(String file) throws IOException {

        List<String> lineList = Files.readAllLines(Paths.get(file));
        lineList.stream()
                .map(s -> s.split(" ").length)
                .sorted(Comparator.reverseOrder()).limit(5).forEach(System.out::println);
    }

    public static void getIntStream() {
        int[] values = {1, 2, 3, 4};
        IntStream is = Arrays.stream(values);

    }

    private static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> firtIte = first.iterator(), secondItr = second.iterator();
        List<T> list = new ArrayList<>();
        while (firtIte.hasNext() && secondItr.hasNext()) {
            list.add(secondItr.next());
            list.add(firtIte.next());
        }
        return list.stream();
    }

    private static void countShortWords(List<String> list, AtomicInteger ai[]) {
        list.stream().parallel().map(String::length).filter(s -> s != 0 && s < 5).forEach(s -> ai[s - 1].getAndIncrement());
    }
    private static void countShortWordsCollect(List<String> list, AtomicInteger ai[]) {
        Map<Integer, Long> map =list.stream().parallel().collect(Collectors.groupingBy(String::length, Collectors.counting()));

    }

}


