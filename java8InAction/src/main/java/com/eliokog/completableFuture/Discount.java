package com.eliokog.completableFuture;

/**
 *
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + "Price is:" + apply(quote.getPrice(), quote.getCode());
    }

    private static double apply(double price, Discount.Code code) {
        delay();
        return price * (1 - code.percentage / 100);
    }
}
