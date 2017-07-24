package com.eliokog.lambda;

        import java.util.Comparator;
        import java.util.Optional;
        import java.util.function.BinaryOperator;
        import java.util.function.Function;
        import java.util.stream.Collectors;

        import static com.eliokog.lambda.Dish.menu;
        import static java.util.stream.Collectors.*;

/**
 */
public class Main {


    public static void main(String[] args) {

        menu.stream().map(Dish::getCalories).max(Integer::compareTo).get();
        System.out.println(menu.stream().collect(summarizingInt(Dish::getCalories)).getSum());
        System.out.println(menu.stream().mapToInt(Dish::getCalories).sum());
        System.out.println(menu.stream().map(Dish::getName).collect(joining("; ")));
        System.out.println(menu.stream().map(Dish::getName).reduce("", (s1, s2) -> s1 + s2 + "; "));
        System.out.println(menu.stream().map(Dish::getName).collect(reducing("", (s1, s2) -> s1 + s2 + "; ")));
        System.out.println(menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2 + "; ")));
        System.out.println(menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() < 400)
                return "DIET";
            else if (dish.getCalories() < 700)
                return "NORMAL";
            else return "HIFH";
        })));

        System.out.println(menu.stream().collect(groupingBy(Dish::getCalories, groupingBy(dish -> {
            if (dish.getCalories() < 400)
                return "DIET";
            else if (dish.getCalories() < 700)
                return "NORMAL";
            else return "HIFH";
        }))));

        System.out.println(menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))));

        System.out.println(menu.stream().collect(toMap(Dish::getType, Function.identity(), BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories)))));


    }
}
