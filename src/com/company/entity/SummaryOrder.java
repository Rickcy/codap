package com.company.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SummaryOrder {
    private final AuctionDirection direction;
    private final int quantity;
    private final double minPrice;
    private final double maxPrice;
    private List<AuctionOrder> orders = new ArrayList<>();

    public SummaryOrder(AuctionDirection direction, int quantity, double minPrice, double maxPrice, List<AuctionOrder> orders) {
        this.quantity = quantity;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.direction = direction;
        this.orders = orders;
    }

    public double getAverage(double top, double bottom) {
        return orders.stream().filter(order -> (top >= order.getPrice()) && (bottom <= order.getPrice())).mapToDouble(AuctionOrder::getPrice).average().orElse(this.minPrice);
    }

    public char getDirection() {
        return direction.toString().charAt(0);
    }

    public int getQuantity() {
        return quantity;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public List<AuctionOrder> getOrders() {
        return Collections.unmodifiableList(orders);
    }


    @Override
    public String toString() {
        return "SummaryOrder{" +
                "direction=" + direction +
                ", quantity=" + quantity +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", orders=" + orders +
                '}';
    }
}
