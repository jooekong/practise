package com.eliokog.completableFuture;

/**
 */
public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code code;

    public Quote(String shopName, double price, Discount.Code code){

        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }

    public static Quote parse(String s ){
        String[] strs = s.split(":");
        return new Quote(strs[0], Double.parseDouble(strs[1]), Discount.Code.valueOf(strs[2]));
    }
}
