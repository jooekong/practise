package com.eliokog.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by eliokog on 2017/5/11.111
 */
public class DiscountShop {

    private String shopName;
    private Random random = new Random();

    public DiscountShop() {

    }

    public String getName() {

        return this.shopName;
    }

    public DiscountShop(String shopname) {
        this.shopName = shopname;
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",  this.shopName, price, code);
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }


    public CompletableFuture<Double> getPriceAsyncCompletable(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
}
