package com.company.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class OptimalBidResult {
    private int quantity = 0;
    private BigDecimal price;

    public OptimalBidResult() {
    }

    public OptimalBidResult(int quantity, double price) {
        this.quantity = quantity;
        this.price = BigDecimal.valueOf(price).setScale(2, RoundingMode.CEILING);
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price != null ? price.toString() : "n/a";
    }
}
