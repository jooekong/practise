package com.eliokog.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by eliokog on 2017/5/11.111
 */
public class Shop {

    private String shopName;
    private Random random = new Random();

    public Shop() {

    }

    public String getName() {

        return this.shopName;
    }

    public Shop(String shopname) {
        this.shopName = shopname;
    }

    public double getPrice(String product) {
        double price = calculatePrice(product);
        return price;
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        return executorService.submit(() -> getPrice(product));
    }

    public CompletableFuture<Double> getPriceAsyncCompletable(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
}
