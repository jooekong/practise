package com.ericsson.eliokog.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;


/**
 * Created by eliokog on 2016/10/10. test the lambda Threadlocal
 */
public class LambdaTest<T extends Collection<Integer>> {
//    public final static ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy"));


    public static void main(String[] args) {
//        Runnable helloWorld = () ->{
//            System.out.println("hi");
//        };
//        System.out.println( Stream.of("a","b").map(String::toUpperCase).collect(Collectors.toList()));
//        LambdaTest<ArrayList<Integer>> tll = new LambdaTest<>();
//        System.out.println(tll.addUp((ArrayList<Integer>)Stream.of(1,2,3).collect(toList())));
//        System.out.println(LambdaTest.getArtistsInfo(Stream.of(new Artist("Tom", "USA", 1), new Artist("Beatles", "UK", 8))
//                .collect(toList())));
//        System.out.println(LambdaTest.countLowerCase("hfadhfaFFFF4444323"));
//        System.out.println(
//                LambdaTest.getMostLowerCaseStr(Stream.of("adfdsfasd", "ffd", "fadsfas").collect(Collectors.toList()))
//        );
//
//        LambdaTest.sortByLength(Arrays.asList("asdasd", "ss", "123")).forEach(System.out::println);
//        LambdaTest.sortByReverseLength(Arrays.asList("asdasd", "ss", "123")).forEach(System.out::println);
//        LambdaTest.sortBy1stChar(Arrays.asList("asdasd", "ss", "123")).forEach(System.out::println);
//
//        LambdaTest.sortByE(Arrays.asList("asdased", "ss", "123", "zzzfee")).forEach(System.out::println);
        LambdaTest.sortByHasE(Arrays.asList("asdased", "ss", "123", "zzzfee")).forEach(System.out::println);


    }

    public  interface Performance{

        public String getName();

        public Stream<Artist> getMusicians();

//        public default Stream<Artist> getAllMusicians(){
//            return getMusicians().flatMap(artist -> {
//                Stream.concat(Stream.of(artist), artist.getMembers())
//            });
//        }
    }

    /**
     *
     * @param list
     * @return
     */
    private static List<String> getArtistsInfo(List<Artist> list){
        return list.stream().map(artist->"Name: " + artist.getName()+ " Country: "+ artist.getCountry()).collect(toList());
    }

    public  int addUp(T t){
       return t.stream().reduce(0,(i, j)-> i+j);
    }

    private static <I,O> List<O> map(Stream<I> stream, Function<I, O> mapper){
       return  stream.reduce(new ArrayList<O>(),
               (acc, x) -> {
                    List<O> newAcc = new ArrayList<O>(acc);
                    newAcc.add(mapper.apply(x));
                    return newAcc;
                },
               (List<O> left, List<O> right) -> {
                   // We are copying left to new list to avoid mutating it.
                   List<O> newLeft = new ArrayList<>(left);
                   newLeft.addAll(right);
                   return newLeft;
               }
        );
    }

//    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
//        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
//            // We are copying data from acc to new list instance. It is very inefficient,
//            // but contract of Stream.reduce method requires that accumulator function does
//            // not mutate its arguments.
//            // Stream.collect method could be used to implement more efficient mutable reduction,
//            // but this exercise asks to use reduce method.
//            List<O> newAcc = new ArrayList<>(acc);
//            newAcc.add(mapper.apply(x));
//            return newAcc;
//        }, (List<O> left, List<O> right) -> {
//            // We are copying left to new list to avoid mutating it.
//            List<O> newLeft = new ArrayList<>(left);
//            newLeft.addAll(right);
//            return newLeft;
//        });
//    }

    private static int countLowerCase(String s){
        return  s.chars().filter(ch -> ( ch>=96&& 123>=ch)).reduce(0,(i , j)->i+1);
    }

    private static  Optional<String> getMostLowerCaseStr(List<String> list){
       return list.stream().max(Comparator.comparing(LambdaTest::countLowerCase));
    }
    static class Artist{
        String name, country;
        int members;
        public Artist(String name, String country, int members){
            this.country = country;
            this.members = members;
            this.name = name;
        }

        public String getCountry() {
            return country;

        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getMembers() {
            return members;
        }

        public void setMembers(int members) {
            this.members = members;
        }

        public String getName() {
            return name;

        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static List<String> sortByLength(List<String> list){
            list.sort(Comparator.comparing(String::length));
            return list;
//        return list.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());

    }
    public static List<String> sortByReverseLength(List<String> list){

        return list.stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());

    }

    public static List<String> sortBy1stChar(List<String> list){

//         list.sort(Comparator.comparing(s->s.charAt(0)));
//        return list;
        return list.stream().sorted(Comparator.comparing(s->s.charAt(0))).collect(toList());
    }

    private static List<String> sortByE(List<String> list){

        Map<Boolean, List<String>> map = list.stream().collect(partitioningBy(s->s.contains("e")));
        List<String> sortedList = map.get(true)
                .stream()
                .sorted(Comparator.comparing(s->s.charAt(0)))
                .collect(toList());
        sortedList.addAll(map
                .get(false)
                .stream()
                .sorted(Comparator.comparing(s->s.charAt(0)))
                .collect(toList()));
        return sortedList;
    }


    private static List<String> sortByHasE(List<String> list){
       return list.stream().sorted((a, b)->{
            if(a.contains("e")){
                return -1;
            }else if(b.contains("e")){
                return 1;
            }else{
                return -1;
            }

        }).collect(toList());
    }

    public static class StringUtils{

        public static String betterString(String s1, String s2, TwoStringPredicate predicate){

            return predicate.isBetterString(s1, s2)?s1:s2;
        }
    }
}
