package com.company.entity;

public class AuctionOrder {
    private final AuctionDirection direction;
    private final int quantity;
    private final double price;

    public AuctionOrder(AuctionDirection direction, int quantity, double price) {
        this.direction = direction;
        this.quantity = quantity;
        this.price = price;
    }

    public AuctionOrder(AuctionDirection direction, String quantity, String price) {
        this.direction = direction;
        this.quantity = Short.parseShort(quantity);
        this.price = Double.parseDouble(price);
    }

    public char getDirection() {
        return direction.toString().charAt(0);
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "direction=" + direction +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
