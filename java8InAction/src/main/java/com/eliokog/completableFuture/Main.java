package com.eliokog.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

/**
 * Created by eliokog on 2017/5/11.
 */
public class Main {

    private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("BestService"),
            new Shop("BestSalewomen"),
            new Shop("BestEnv"),
            new Shop("normalShop"),
            new Shop("worseShop"),
            new Shop("badShop"));

    private static List<DiscountShop> discountShops = Arrays.asList(new DiscountShop("BestPrice"),
            new DiscountShop("BestService"),
            new DiscountShop("BestSalewomen"),
            new DiscountShop("BestEnv"),
            new DiscountShop("normalShop"),
            new DiscountShop("worseShop"),
            new DiscountShop("badShop"));

    private static Executor executor = Executors.newFixedThreadPool(Math.min(100, shops.size()), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void main(String[] args) {
//        Shop shop = new Shop();
//        Future<Double> future = shop.getPriceAsync("Chanel");
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

//        CompletableFuture<Double> completableFuture = shop.getPriceAsyncCompletable("Chanel");
//        try {
//            System.out.println(completableFuture.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        long startTime = System.nanoTime();
//        findPrice("Chanel").forEach(System.out::println);
//        long duration = (System.nanoTime() - startTime) / 1_000_000;
//        System.out.println("normal stream duration is: " + duration);
//
//        long startTim1e = System.nanoTime();
//        findPriceParallel("Chanel").forEach(System.out::println);
//        long duration1 = (System.nanoTime() - startTim1e) / 1_000_000;
//        System.out.println("parallel stream duration is: " + duration1);

//        long startTime2 = System.nanoTime();
//        findPriceCompletable("Chanel").forEach(System.out::println);
//        long duration2 = (System.nanoTime() - startTime2) / 1_000_000;
//        System.out.println("completableFutre  duration is: " + duration2);

        long startTime3 = System.nanoTime();
        findPriceWithDiscount("Chanel").forEach(System.out::println);
        long duration3 = (System.nanoTime() - startTime3) / 1_000_000;
        System.out.println("completableFutre discount compose  duration is: " + duration3);

    }

    private static List<String> findPrice(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    private static List<String> findPriceParallel(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(toList());
    }

    private static List<String> findPriceCompletable(String product) {
        long startTime2 = System.nanoTime();
        //why 2 seconds consumed here??
        List<CompletableFuture<String>> futureList = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor)).collect(toList());
        long startTime1 = System.nanoTime();
        System.out.println("completableFutre  list duration is: " + (startTime1 - startTime2) / 1_000_000);

        return futureList.parallelStream().map(CompletableFuture::join).collect(toList());
    }


    private static List<String> findPriceWithDiscount(String product) {

        List<CompletableFuture<String>> list = discountShops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor)).map(f -> f.thenApply(Quote::parse)).map(f -> f.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor))).collect(toList());
        return list.stream().map(CompletableFuture::join).collect(toList());
    }
}
